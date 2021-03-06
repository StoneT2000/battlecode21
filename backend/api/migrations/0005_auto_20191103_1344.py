# Generated by Django 2.2.4 on 2019-11-03 13:44

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('api', '0004_auto_20191103_1312'),
    ]

    operations = [
        migrations.RenameField(
            model_name='submission',
            old_name='team_id',
            new_name='team',
        ),
        migrations.RenameField(
            model_name='teamsubmission',
            old_name='team_id',
            new_name='team',
        ),
        migrations.AlterField(
            model_name='teamsubmission',
            name='compiling',
            field=models.ForeignKey(blank=True, default=None, null=True, on_delete=django.db.models.deletion.PROTECT, related_name='compiling', to='api.Submission'),
        ),
        migrations.AlterField(
            model_name='teamsubmission',
            name='last_1',
            field=models.ForeignKey(blank=True, default=None, null=True, on_delete=django.db.models.deletion.PROTECT, related_name='last_1', to='api.Submission'),
        ),
        migrations.AlterField(
            model_name='teamsubmission',
            name='last_2',
            field=models.ForeignKey(blank=True, default=None, null=True, on_delete=django.db.models.deletion.PROTECT, related_name='last_2', to='api.Submission'),
        ),
        migrations.AlterField(
            model_name='teamsubmission',
            name='last_3',
            field=models.ForeignKey(blank=True, default=None, null=True, on_delete=django.db.models.deletion.PROTECT, related_name='last_3', to='api.Submission'),
        ),
        migrations.AlterField(
            model_name='teamsubmission',
            name='tour_final',
            field=models.ForeignKey(blank=True, default=None, null=True, on_delete=django.db.models.deletion.PROTECT, related_name='tour_final', to='api.Submission'),
        ),
        migrations.AlterField(
            model_name='teamsubmission',
            name='tour_qual',
            field=models.ForeignKey(blank=True, default=None, null=True, on_delete=django.db.models.deletion.PROTECT, related_name='tour_qual', to='api.Submission'),
        ),
        migrations.AlterField(
            model_name='teamsubmission',
            name='tour_seed',
            field=models.ForeignKey(blank=True, default=None, null=True, on_delete=django.db.models.deletion.PROTECT, related_name='tour_seed', to='api.Submission'),
        ),
        migrations.AlterField(
            model_name='teamsubmission',
            name='tour_sprint',
            field=models.ForeignKey(blank=True, default=None, null=True, on_delete=django.db.models.deletion.PROTECT, related_name='tour_sprint', to='api.Submission'),
        ),
    ]
