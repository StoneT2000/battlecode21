package battlecode.common;
import java.util.ArrayList;

/**
 * A RobotController allows contestants to make their robot sense and interact
 * with the game world. When a contestant's <code>RobotPlayer</code> is
 * constructed, it is passed an instance of <code>RobotController</code> that
 * controls the newly created robot.
 */
@SuppressWarnings("unused")
public strictfp interface RobotController {

    // *********************************
    // ****** GLOBAL QUERY METHODS *****
    // *********************************

    /**
     * Returns the current round number, where round 1 is the first round of the
     * match.
     *
     * @return the current round number, where round 1 is the first round of the
     * match.
     *
     * @battlecode.doc.costlymethod
     */
    int getRoundNum();

    /**
     * Returns the team's total votes.
     *
     * @return the team's total votes.
     *
     * @battlecode.doc.costlymethod
     */
    int getTeamVotes();

    /**
     * Returns the number of robots on your team, including Centers of Enlightenment.
     * If this number ever reaches zero, you immediately lose.
     *
     * @return the number of robots on your team
     *
     * @battlecode.doc.costlymethod
     */
    int getRobotCount();

    // *********************************
    // ****** UNIT QUERY METHODS *******
    // *********************************

    /**
     * Returns the ID of this robot.
     *
     * @return the ID of this robot.
     *
     * @battlecode.doc.costlymethod
     */
    int getID();

    /**
     * Returns this robot's Team.
     *
     * @return this robot's Team.
     *
     * @battlecode.doc.costlymethod
     */
    Team getTeam();

    /**
     * Returns this robot's type (MUCKRAKER, POLITICIAN, SLANDERER, etc.).
     *
     * @return this robot's type.
     *
     * @battlecode.doc.costlymethod
     */
    RobotType getType();

    /**
     * Returns this robot's current location.
     *
     * @return this robot's current location.
     *
     * @battlecode.doc.costlymethod
     */
    MapLocation getLocation();


    // ***********************************
    // ****** GENERAL SENSOR METHODS *****
    // ***********************************

    /**
     * Senses whether a MapLocation is on the map. Will throw an exception if
     * the location is not within the sensor range.
     *
     * @param loc the location to check
     * @return true if the location is on the map; false otherwise.
     * @throws GameActionException if the location is not within sensor range.
     *
     * @battlecode.doc.costlymethod
     */
    boolean onTheMap(MapLocation loc) throws GameActionException;

    /**
     * Checks whether the given location is within the robot's sensor range, and if it is on the map.
     *
     * @param loc the location to check
     * @return true if the given location is within the robot's sensor range and is on the map; false otherwise.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canSenseLocation(MapLocation loc);

    /**
     * Checks whether a point at the given radius squared is within the robot's sensor range.
     *
     * @param radiusSquared the radius to check
     * @return true if the given radius is within the robot's sensor range; false otherwise.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canSenseRadiusSquared(int radiusSquared);

    /**
     * Checks whether the given location is within the robot's detection range, and if it is on the map.
     *
     * @param loc the location to check
     * @return true if the given location is within the robot's detection range and is on the map; false otherwise.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canDetectLocation(MapLocation loc);

    /**
     * Checks whether a point at the given radius squared is within the robot's detection range.
     *
     * @param radiusSquared the radius to check
     * @return true if the given radius is within the robot's detection range; false otherwise.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canDetectRadiusSquared(int radiusSquared);

    /**
     * Detects whether there is a robot at the given location.
     *
     * @param loc the location to check
     * @return true if there is a robot at the given location; false otherwise.
     * @throws GameActionException if the location is not within detection range.
     *
     * @battlecode.doc.costlymethod
     */
    boolean isLocationOccupied(MapLocation loc) throws GameActionException;

    /**
     * Senses the robot at the given location, or null if there is no robot
     * there.
     *
     * @param loc the location to check
     * @return the robot at the given location.
     * @throws GameActionException if the location is not within sensor range.
     *
     * @battlecode.doc.costlymethod
     */
    RobotInfo senseRobotAtLocation(MapLocation loc) throws GameActionException;

    /**
     * Tests whether the given robot exists and if it is within this robot's
     * sensor range.
     *
     * @param id the ID of the robot to query
     * @return true if the given robot is within this robot's sensor range;
     * false otherwise.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canSenseRobot(int id);

    /**
     * Senses information about a particular robot given its ID.
     *
     * @param id the ID of the robot to query
     * @return a RobotInfo object for the sensed robot.
     * @throws GameActionException if the robot cannot be sensed (for example,
     * if it doesn't exist or is out of sensor range).
     *
     * @battlecode.doc.costlymethod
     */
    RobotInfo senseRobot(int id) throws GameActionException;

    /**
     * Returns all robots within sensor radius. The objects are returned in no
     * particular order.
     *
     * @return array of RobotInfo objects, which contain information about all
     * the robots you sensed.
     *
     * @battlecode.doc.costlymethod
     */
    RobotInfo[] senseNearbyRobots();

    /**
     * Returns all robots that can be sensed within a certain distance of this
     * robot. The objects are returned in no particular order.
     *
     * @param radiusSquared return robots this distance away from the center of
     * this robot. If -1 is passed, all robots within sense radius are returned.
     * @return array of RobotInfo objects of all the robots you sensed.
     *
     * @battlecode.doc.costlymethod
     */
    RobotInfo[] senseNearbyRobots(int radiusSquared);

    /**
     * Returns all robots of a given team that can be sensed within a certain
     * distance of this robot. The objects are returned in no particular order.
     *
     * @param radiusSquared return robots this distance away from the center of
     * this robot. If -1 is passed, all robots within sense radius are returned
     * @param team filter game objects by the given team. If null is passed,
     * robots from any team are returned
     * @return array of RobotInfo objects of all the robots you sensed.
     *
     * @battlecode.doc.costlymethod
     */
    RobotInfo[] senseNearbyRobots(int radiusSquared, Team team);

    /**
     * Returns all robots of a given team that can be sensed within a certain
     * radius of a specified location. The objects are returned in order of
     * increasing distance from the specified center.
     *
     * @param center center of the given search radius
     * @param radius return robots this distance away from the given center
     * location. If -1 is passed, all robots within sense radius are returned
     * @param team filter game objects by the given team. If null is passed,
     * objects from all teams are returned
     * @return sorted array of RobotInfo objects of the robots you sensed.
     *
     * @battlecode.doc.costlymethod
     */
    RobotInfo[] senseNearbyRobots(MapLocation center, int radius, Team team);

    /**
     * Returns locations of all robots within detection radius. The objects are
     * returned in no particular order.
     *
     * @return array of MapLocation objects, which are the locations of all the
     * robots you detected.
     *
     * @battlecode.doc.costlymethod
     */
    MapLocation[] detectNearbyRobots();

    /**
     * Returns locations of all robots that can be detected within a certain
     * distance of this robot. The objects are returned in no particular order.
     *
     * @param radiusSquared return robots this distance away from the center of
     * this robot. If -1 is passed, all robots within detection radius are
     * returned.
     * @return array of MapLocation objects of all the robots you detected.
     *
     * @battlecode.doc.costlymethod
     */
    MapLocation[] detectNearbyRobots(int radiusSquared);

    /**
     * Returns locations of all robots of a given team that can be detected
     * within a certain distance of this robot. The objects are returned in no
     * particular order.
     *
     * @param radiusSquared return robots this distance away from the center of
     * this robot. If -1 is passed, all robots within detection radius are
     * returned.
     * @param team filter game objects by the given team. If null is passed,
     * robots from any team are returned
     * @return array of MapLocation objects of all the robots you detected.
     *
     * @battlecode.doc.costlymethod
     */
    MapLocation[] detectNearbyRobots(int radiusSquared, Team team);

    /**
     * Returns all robots of a given team that can be detected within a certain
     * radius of a specified location. The objects are returned in order of
     * increasing distance from the specified center.
     *
     * @param center center of the given search radius
     * @param radius return robots this distance away from the given center
     * location. If -1 is passed, all robots within detected radius are returned
     * @param team filter game objects by the given team. If null is passed,
     * objects from all teams are returned.
     * @return array of MapLocation objects of all the robots you detected.
     *
     * @battlecode.doc.costlymethod
     */
    MapLocation[] detectNearbyRobots(MapLocation center, int radius, Team team);

    /**
     * Given a location, returns the passability of that location.
     *
     * Lower passability means that robots on this location may be penalized
     * greater cooldowns for making actions.
     * 
     * @param loc the given location
     * @return the passability of that location.
     * @throws GameActionException if the robot cannot sense the given location
     *
     * @battlecode.doc.costlymethod
     */
    double sensePassability(MapLocation loc) throws GameActionException;

    /**
     * Returns the location adjacent to current location in the given direction.
     *
     * @param dir the given direction
     * @return the location adjacent to current location in the given direction.
     *
     * @battlecode.doc.costlymethod
     */
    MapLocation adjacentLocation(Direction dir);

    // ***********************************
    // ****** READINESS METHODS **********
    // ***********************************

    /**
     * Tests whether the robot can perform an action. Returns
     * <code>getCooldownTurns() &lt; 1</code>.
     * 
     * @return true if the robot can perform an action.
     *
     * @battlecode.doc.costlymethod
     */
    boolean isReady();

    /**
     * Returns the number of cooldown turns remaining before this unit can act again.
     * When this number is strictly less than 1, isReady() is true and the robot
     * can perform actions again.
     *
     * @return the number of cooldown turns remaining before this unit can act again.
     *
     * @battlecode.doc.costlymethod
     */
    float getCooldownTurns();

    // ***********************************
    // ****** MOVEMENT METHODS ***********
    // ***********************************

    /**
     * Checks whether this robot can move one step in the given direction.
     * Returns false if the robot is a building, if the target location is not
     * on the map, if the target location is occupied, or if there are cooldown
     * turns remaining.
     *
     * @param dir the direction to move in
     * @return true if it is possible to call <code>move</code> without an exception
     *
     * @battlecode.doc.costlymethod
     */
    boolean canMove(Direction dir);

    /**
     * Moves one step in the given direction.
     *
     * @param dir the direction to move in
     * @throws GameActionException if the robot cannot move one step in this
     * direction, such as cooldown being &gt;= 1, the target location being
     * off the map, or the target destination being occupied by
     * another robot.
     *
     * @battlecode.doc.costlymethod
     */
    void move(Direction dir) throws GameActionException;

    // ***********************************
    // ****** BUILDING/SPAWNING **********
    // ***********************************

    /**
     * Tests whether the robot can build a robot of the given type in the
     * given direction. Checks that the robot is of a type that can build,
     * that the robot can build the desired type, that the target location is
     * on the map, that the target location is not occupied, that the robot has
     * the amount of influence it's trying to spend, and that there are no
     * cooldown turns remaining.
     *
     * @param type the type of robot to build
     * @param dir the direction to build in
     * @param influence the amount of influence to spend
     * @return whether it is possible to build a robot of the given type in the
     * given direction.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canBuildRobot(RobotType type, Direction dir, int influence);

    /**
     * Builds a robot of the given type in the given direction.
     *
     * @param type the type of robot to build
     * @param dir the direction to spawn the unit
     * @param influence the amount of influence to be used to build
     * @throws GameActionException if the conditions of <code>canBuildRobot</code>
     * are not all satisfied.
     *
     * @battlecode.doc.costlymethod
     */
    void buildRobot(RobotType type, Direction dir, int influence) throws GameActionException;

    // ***********************************
    // ****** POLITICIAN METHODS ********* 
    // ***********************************

    /**
     * Tests whether the robot can empower.
     * Checks that the robot is a politician, and if there are no cooldown turns
     * remaining.
     * 
     * @return whether it is possible to empower on that round.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canEmpower(int radiusSquared);

    /**
     * Runs the "empower" ability of a politician.
     *
     * Friendly units will have conviction increased, and unfriendly units will
     * have conviction decreased. Enemy politicians and buildings with negative conviction
     * will join your team.
     *
     * This also causes the politician unit to self-destruct; on the next round
     * it will no longer be in the world.
     *
     * @throws GameActionException if conditions for empowering are not all satisfied
     * @battlecode.doc.costlymethod
     */
    void empower(int radiusSquared) throws GameActionException;


    // ***********************************
    // ****** MUCKRAKER METHODS ********** 
    // ***********************************

    /**
     * Tests whether the robot can expose at a given location.
     * Checks that the robot is a muckraker, that the robot is within action
     * radius of the muckraker, that there are no cooldown turns remaining, and
     * that a slanderer is present on the location.
     *
     * @param loc the location being checked
     * @return whether it is possible to expose on that round at that location.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canExpose(MapLocation loc);

<<<<<<< Updated upstream
    /**
     * Tests whether the robot can expose a given robot.
     * Checks that the robot is a muckraker, that the targeted robot is an enemy
     * slanderer, that the robot is within action radius, and that there are no
     * cooldown turns remaining.
     *
     * @param id the robot being checked
     * @return whether it is possible to expose that robot on this round.
     *
=======
    /** 
     * Given a location, exposes a slanderer on that location, if a slanderer exists on that location.
     * If a slanderer is exposed then on the next round it will no longer be in the world.
     * Aside from this, a successful expose temporarily increases the total conviction
     * of all Politicians on the same team by a factor (GameConstants.EXPOSE_FACTOR)^(influence) for the next
     * <code> GameConstants.EXPOSE_NUM_TURNS </code> turns
     *
     * If the conditions for exposing are all met but loc does not contain a slanderer,
     * an Exception is thrown, and the bytecode and cooldown costs are still consumed. 
     * @throws GameActionException if conditions for exposing are not all satisfied 
>>>>>>> Stashed changes
     * @battlecode.doc.costlymethod
     */
    boolean canExpose(int id);

    /**
     * Exposes a slanderer at a given location.
     * The slanderer will be destroyed, and all attempts to empower by friendly
     * Politicians will be temporarily increased by a multiplicative factor.
     *
     * @throws GameActionException if conditions for exposing are not all satisfied 
     *
     * @battlecode.doc.costlymethod
     */
    void expose(MapLocation loc) throws GameActionException;


    // **************************************
    // *** CENTER OF ENLIGHTENMENT METHODS **
    // **************************************

    /**
     * Tests whether the robot can bid the specified amount of influence on that round.
     * 
     * Checks that the robot is an Enlightenment Center, that the robot has at least
     * that amount of influence, and that the amount of influence is non-negative.
     *
     * @param influence the amount of influence being bid 
     * @return whether it is possible to bid that amount of influence.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canBid(int influence);

    /** 
     * Enter an influence bid for the vote on that turn.
     *
     * @throws GameActionException if conditions for bidding are not satisfied
     * @battlecode.doc.costlymethod 
     */
    void bid(int influence) throws GameActionException;

    // ***********************************
    // ****** COMMUNICATION METHODS ****** 
    // ***********************************

    /** 
     * Sets a robot's flag to an integer.
     *
     * @param flag the flag value.
     *
     * @battlecode.doc.costlymethod
     */
    void setFlag(int flag) throws GameActionException;

    /**
     * Given a MapLocation, checks if a robot can get the flag of the robot on that location,
     * if a robot exists there.
     *
     * Checks that a robot is on the MapLocation given, that the robot on the
     * target location is on the same team, and that either (a) the robot is an
     * Enlightenment Center or (b) the squared distance between the target
     * location and the current location is &leq; 8.
     *
     * @param loc MapLocation being targeted by canGetFlag
     * @return whether it is possible to set the robot's flag on that round.
     *
     * @battlecode.doc.costlymethod
     */
    boolean canGetFlag(MapLocation loc);

    /** 
     * Given a MapLocation, returns a the flag of the robot at that location.
     *
     * @param loc MapLocation being targeted by getFlag
     * @throws GameActionException if conditions for getting the flag are not satisfied
     * @return the flag of the robot on the location specified
     *
     * @battlecode.doc.costlymethod
     */
    int getFlag(MapLocation loc) throws GameActionException;


    // ***********************************
    // ****** OTHER ACTION METHODS *******
    // ***********************************

    /**
     * Causes your team to lose the game. It's like typing "gg."
     *
     * @battlecode.doc.costlymethod
     */
    void resign();

    // ***********************************
    // ******** DEBUG METHODS ************
    // ***********************************

    /**
     * Draw a dot on the game map for debugging purposes.
     *
     * @param loc the location to draw the dot.
     * @param red the red component of the dot's color.
     * @param green the green component of the dot's color.
     * @param blue the blue component of the dot's color.
     *
     * @battlecode.doc.costlymethod
     */
    void setIndicatorDot(MapLocation loc, int red, int green, int blue);

    /**
     * Draw a line on the game map for debugging purposes.
     *
     * @param startLoc the location to draw the line from.
     * @param endLoc the location to draw the line to.
     * @param red the red component of the line's color.
     * @param green the green component of the line's color.
     * @param blue the blue component of the line's color.
     *
     * @battlecode.doc.costlymethod
     */
    void setIndicatorLine(MapLocation startLoc, MapLocation endLoc, int red, int green, int blue);
}
