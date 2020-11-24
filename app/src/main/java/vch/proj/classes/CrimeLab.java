package vch.proj.classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import vch.proj.db.CrimeBaseHelper;

import static vch.proj.classes.VchHelper.l;

import vch.proj.db.CrimeCursorWrapper;
import vch.proj.db.CrimeDbSchema.CrimeTable;

public class CrimeLab {

    protected static CrimeLab sCrimeLab;
    protected List<Crime> mCrimes;
    protected Context mContext;
    protected SQLiteDatabase mDatabase;

    public static CrimeLab get(Context context) {
        if (sCrimeLab == null) {
            sCrimeLab = new CrimeLab(context);
        }
        return sCrimeLab;
    }

    public void addCrime(Crime crime) {
//        mCrimes.add(c);
        ContentValues values = getContentValues(crime);
        mDatabase.insert(CrimeTable.NAME, null, values);
    }

    private CrimeLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new CrimeBaseHelper(mContext)
                .getWritableDatabase();
//        mCrimes = new ArrayList<>();
    }

    public List<Crime> getCrimes() {
//        return mCrimes;
        List<Crime> crimes = new ArrayList<>();
        CrimeCursorWrapper cursor = queryCrimes(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                crimes.add(cursor.getCrime());
                cursor.moveToNext();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            cursor.close();
        }

        return crimes;
    }

    public File getPhotoFile(Crime crime) {
        File filesDir = mContext.getFilesDir();
        return new File(filesDir, crime.getPhotoFilename());
    }

    public Crime getCrime(UUID id) {
        Crime crime;
        CrimeCursorWrapper cursor = queryCrimes(
                CrimeTable.Cols.UUID + " = ?",
                new String[] {String.valueOf(id)});

        try {
            if (null == cursor) {
                return null;
            }

            cursor.moveToFirst();
            return cursor.getCrime();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            cursor.close();
        }
    }

    public void updateCrime(Crime crime) {
        String id = crime.getId().toString();
        ContentValues values = getContentValues(crime);

        mDatabase.update(CrimeTable.NAME,
                values,
                CrimeTable.Cols.UUID + " = ?",
                new String[] {id});
    }

    protected CrimeCursorWrapper queryCrimes(String where, String[] args) {
        Cursor cursor = mDatabase.query(
                CrimeTable.NAME,
                null,
                where,
                args,
                null,
                null,
                null
        );

        return new CrimeCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Crime crime) {
        ContentValues values = new ContentValues();
        values.put(CrimeTable.Cols.UUID, crime.getId().toString());
        values.put(CrimeTable.Cols.TITLE, crime.getTitle());
        values.put(CrimeTable.Cols.DATE, crime.getDate().getTime());
        values.put(CrimeTable.Cols.SOLVED, crime.isSolved() ? 1 : 0);
        values.put(CrimeTable.Cols.SUSPECT, crime.getSuspect());
        return values;
    }
}
