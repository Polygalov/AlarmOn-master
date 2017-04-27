/****************************************************************************
 * Copyright 2010 kraigs.android@gmail.com
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ****************************************************************************/

package io.github.carlorodriguez.alarmon;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

/**
 * This class contains all of the settings data for a given alarm.  It also
 * provides the mapping from this data to the respective columns in the
 * persistent settings database.
 */
public final class AlarmSettings {
    static public final long DEFAULT_SETTINGS_ID = -1;

    private Uri tone;
    private String toneName;
    private int snoozeMinutes;
    private boolean vibrate;
    private int volumeStartPercent;
    private int volumeEndPercent;
    private int volumeChangeTimeSec;
    private int volumePercent;
    private int lengthSignal;
    private int pauseBeetweenSignals;
    private int numberOfSignals;

    public ContentValues contentValues(long alarmId) {
        ContentValues values = new ContentValues();
        values.put(DbHelper.SETTINGS_COL_ID, alarmId);
        values.put(DbHelper.SETTINGS_COL_TONE_URL, tone.toString());
        values.put(DbHelper.SETTINGS_COL_TONE_NAME, toneName);
        values.put(DbHelper.SETTINGS_COL_SNOOZE, snoozeMinutes);
        values.put(DbHelper.SETTINGS_COL_VIBRATE, vibrate);
        values.put(DbHelper.SETTINGS_COL_VOLUME_STARTING, volumeStartPercent);
        values.put(DbHelper.SETTINGS_COL_VOLUME_ENDING, volumeEndPercent);
        values.put(DbHelper.SETTINGS_COL_VOLUME_TIME, volumeChangeTimeSec);
        values.put(DbHelper.SETTINGS_COL_VOLUME, volumePercent);
        values.put(DbHelper.SETTINGS_COL_LENGTH_SIGNAL, lengthSignal);
        values.put(DbHelper.SETTINGS_COL_PAUSE_SIGNALS, pauseBeetweenSignals);
        values.put(DbHelper.SETTINGS_COL_NUMBER_SIGNALS, numberOfSignals);
        return values;
    }

    static public String[] contentColumns() {
        return new String[]{
                DbHelper.SETTINGS_COL_ID,
                DbHelper.SETTINGS_COL_TONE_URL,
                DbHelper.SETTINGS_COL_TONE_NAME,
                DbHelper.SETTINGS_COL_SNOOZE,
                DbHelper.SETTINGS_COL_VIBRATE,
                DbHelper.SETTINGS_COL_VOLUME_STARTING,
                DbHelper.SETTINGS_COL_VOLUME_ENDING,
                DbHelper.SETTINGS_COL_VOLUME_TIME,
                DbHelper.SETTINGS_COL_VOLUME,
                DbHelper.SETTINGS_COL_LENGTH_SIGNAL,
                DbHelper.SETTINGS_COL_PAUSE_SIGNALS,
                DbHelper.SETTINGS_COL_NUMBER_SIGNALS
        };
    }

    public AlarmSettings() {
        tone = AlarmUtil.getDefaultAlarmUri();
        toneName = "Default";
        snoozeMinutes = 10;
        vibrate = false;
        volumeStartPercent = 0;
        volumeEndPercent = 100;
        volumeChangeTimeSec = 20;
        volumePercent = 100;
        lengthSignal = 9;
        pauseBeetweenSignals = 9;
        numberOfSignals = 9;
    }

    public AlarmSettings(AlarmSettings rhs) {
        tone = rhs.tone;
        toneName = rhs.toneName;
        snoozeMinutes = rhs.snoozeMinutes;
        vibrate = rhs.vibrate;
        volumeStartPercent = rhs.volumeStartPercent;
        volumeEndPercent = rhs.volumeEndPercent;
        volumeChangeTimeSec = rhs.volumeChangeTimeSec;
        volumePercent = rhs.volumePercent;
        lengthSignal = rhs.lengthSignal;
        pauseBeetweenSignals = rhs.pauseBeetweenSignals;
        numberOfSignals = rhs.numberOfSignals;
    }

    public AlarmSettings(Cursor cursor) {
        cursor.moveToFirst();
        tone = Uri.parse(cursor.getString(cursor.getColumnIndex(DbHelper.SETTINGS_COL_TONE_URL)));
        toneName = cursor.getString(cursor.getColumnIndex(DbHelper.SETTINGS_COL_TONE_NAME));
        snoozeMinutes = cursor.getInt(cursor.getColumnIndex(DbHelper.SETTINGS_COL_SNOOZE));
        vibrate = cursor.getInt(cursor.getColumnIndex(DbHelper.SETTINGS_COL_VIBRATE)) == 1;
        volumeStartPercent = cursor.getInt(cursor.getColumnIndex(DbHelper.SETTINGS_COL_VOLUME_STARTING));
        volumeEndPercent = cursor.getInt(cursor.getColumnIndex(DbHelper.SETTINGS_COL_VOLUME_ENDING));
        volumeChangeTimeSec = cursor.getInt(cursor.getColumnIndex(DbHelper.SETTINGS_COL_VOLUME_TIME));
        volumePercent = cursor.getInt(cursor.getColumnIndex(DbHelper.SETTINGS_COL_VOLUME));
        lengthSignal = cursor.getInt(cursor.getColumnIndex(DbHelper.SETTINGS_COL_LENGTH_SIGNAL));
        pauseBeetweenSignals = cursor.getInt(cursor.getColumnIndex(DbHelper.SETTINGS_COL_PAUSE_SIGNALS));
        numberOfSignals = cursor.getInt(cursor.getColumnIndex(DbHelper.SETTINGS_COL_NUMBER_SIGNALS));
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AlarmSettings)) {
            return false;
        }
        AlarmSettings rhs = (AlarmSettings) o;
        return tone.equals(rhs.tone)
                && toneName.equals(rhs.toneName)
                && snoozeMinutes == rhs.snoozeMinutes
                && vibrate == rhs.vibrate
                && volumeStartPercent == rhs.volumeStartPercent
                && volumeEndPercent == rhs.volumeEndPercent
                && volumePercent == rhs.volumePercent
                && lengthSignal == rhs.lengthSignal
                && pauseBeetweenSignals == rhs.pauseBeetweenSignals
                && lengthSignal == rhs.lengthSignal
                && numberOfSignals == rhs.numberOfSignals;
    }

    public Uri getTone() {
        //  return tone;
        return Uri.parse("android.resource://io.github.carlorodriguez.alarmon/" + R.raw.myz);
    }

    public void setTone(Uri tone, String name) {
        this.tone = tone;
        this.toneName = name;
    }

    public String getToneName() {
        return toneName;
    }

    public int getSnoozeMinutes() {
        return snoozeMinutes;
    }

    public void setSnoozeMinutes(int minutes) {
        if (minutes < 1) {
            minutes = 1;
        } else if (minutes > 60) {
            minutes = 60;
        }
        this.snoozeMinutes = minutes;
    }

    public boolean getVibrate() {
        return vibrate;
    }

    public void setVibrate(boolean vibrate) {
        this.vibrate = vibrate;
    }

    public int getVolumeStartPercent() {
        return volumeStartPercent;
    }


    public int getLengthSignal() {
        return lengthSignal;
    }

    public void setLengthSignal(int lengthSignal) {
        if (lengthSignal < 0) {
            lengthSignal = 0;
        } else if (lengthSignal > 49) {
            lengthSignal = 49;
        }
        this.lengthSignal = lengthSignal;
    }


    public int getVolumePercent() {
        return volumePercent;
    }

    public void setVolumePercent(int volumePercent) {
        if (volumePercent < 0) {
            volumePercent = 0;
        } else if (volumePercent > 100) {
            volumePercent = 100;
        }
        this.volumePercent = volumePercent;
    }


    public int getPauseBeetweenSignals() {
        return pauseBeetweenSignals;
    }

    public void setPauseBeetweenSignals(int pauseBeetweenSignals) {
        if (pauseBeetweenSignals < 0) {
            pauseBeetweenSignals = 0;
        } else if (pauseBeetweenSignals > 49) {
            pauseBeetweenSignals = 49;
        }
        this.pauseBeetweenSignals = pauseBeetweenSignals;
    }


    public int getNumberOfSignals() {
        return numberOfSignals;
    }

    public void setNumberOfSignals(int numberOfSignals) {
        if (numberOfSignals < 0) {
            numberOfSignals = 0;
        } else if (numberOfSignals > 99) {
            numberOfSignals = 99;
        }
        this.numberOfSignals = numberOfSignals;
    }


    public int getVolumeEndPercent() {
        return volumeEndPercent;
    }


    public int getVolumeChangeTimeSec() {
        return volumeChangeTimeSec;
    }


}
