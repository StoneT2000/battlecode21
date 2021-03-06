# Requires achallonge, and _not_ pychal. Make sure to `pip uninstall pychal`, `pip install achallonge` etc before using.

# Usage:
# python challonge_pubber.py argv
# argv[1]: a json produced by running the tournmanet
# argv[2]: the challonge match number (as shown on the bracket page) of the first Challonge match (or, if argv[3] not specified, the only challonge match) whose result is to be published
# argv[3]: optional; the challonge match number of the last Challonge match to be published, _exclusive_.

# IMPORTANT -- Before running this:
# Ensure you have achallonge, and not pychal, installed as Python packages.
# Get the Challonge API Key. Set it to an env, CHALLONGE_API_KEY. DON'T PUSH IT!
# Get the tournament url, it's the alphanumeric string at the end of the tournament website's url. (e.g. http://challonge.com/thispart). Set it to an env, CHALLONGE_TOUR_URL.
# Get the lowest Challonge match id (see some commented code for an example). Set it to an env, CHALLONGE_LOWEST_ID.
# Ensure the tournament is started and attachments are allowed (see some commented code for more info).

import sys, json, challonge, asyncio, os

replay_file_name = sys.argv[1]
match_no_start = int(sys.argv[2])
try:
    match_no_end = int(sys.argv[3])
except:
    match_no_end = match_no_start+1


async def run():
    with open(replay_file_name, 'r') as replay_file:
        replays = json.load(replay_file)
        api_key = os.getenv('CHALLONGE_API_KEY')
        user = await challonge.get_user('mitbattlecode',api_key)
        
        tour_url = os.getenv('CHALLONGE_TOUR_URL')
        tournament = await user.get_tournament(url = tour_url)
        # # To ensure tournament is started and attachments are allowed; only needs to be run once
        # await tournament.start()
        # await tournament.allow_attachments(True)

        # # For getting the lowest challonge match id:
        # tournament_matches = await tournament.get_matches()
        # for m in tournament_matches:
        #     print(m.id)
        # # (then look through this)
        lowest_id = int(os.getenv('CHALLONGE_LOWEST_ID'))


        for match_no in range(match_no_start, match_no_end):
            print(f'Reporting Challonge match {match_no}')
            match = replays[match_no - 1] # note -1, for proper indexing: challonge is 1-indexed while the json is 0
            api_match = await tournament.get_match(match_no + lowest_id - 1) # note -1, for proper indexing: lowest_id corresponds to match number 1
            api_player1 = await tournament.get_participant( api_match.player1_id )
            api_player2 = await tournament.get_participant( api_match.player2_id )


            player1 = match[0][0]
            player2 = match[0][1]

            player1_score = 0
            player2_score = 0

            for game in match:
                p_red = game[0]
                p_blue = game[1]
                map = game[2]
                winner = p_red if game[3] == 1 else p_blue
                replay = game[4]

                if winner == player1:
                    player1_score += 1
                else:
                    player2_score += 1

                replayurl = f'http://2021.battlecode.org/visualizer.html?tournamentMode&https://2021.battlecode.org/replays/{replay}.bc21'
                await api_match.attach_url(replayurl)

            if player1_score > player2_score:
                await api_match.report_winner(api_player1, f'{player1_score}-{player2_score}')
            else:
                await api_match.report_winner(api_player2, f'{player1_score}-{player2_score}')

            print(f'Challonge match {match_no} reported!')


loop = asyncio.get_event_loop()
loop.run_until_complete(run())
loop.close()
