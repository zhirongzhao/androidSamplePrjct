package com.sample.androidsampleprjct.dao.lottery;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import com.sample.androidsampleprjct.util.db.DaoSession;
import com.sample.androidsampleprjct.vo.Lottery;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LT_LOTTERY".
*/
public class LotteryDao extends AbstractDao<Lottery, Long> {

    public static final String TABLENAME = "LT_LOTTERY";

    /**
     * Properties of entity Lottery.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property LotteryCode = new Property(1, String.class, "lotteryCode", false, "LOTTERY_CODE");
        public final static Property Expect = new Property(2, String.class, "expect", false, "EXPECT");
        public final static Property CandidateCode = new Property(3, String.class, "candidateCode", false, "CANDIDATE_CODE");
        public final static Property CandidateCode2 = new Property(4, String.class, "candidateCode2", false, "CANDIDATE_CODE2");
        public final static Property RegDate = new Property(5, java.util.Date.class, "regDate", false, "REG_DATE");
        public final static Property IsWin = new Property(6, String.class, "isWin", false, "IS_WIN");
    };


    public LotteryDao(DaoConfig config) {
        super(config);
    }
    
    public LotteryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LT_LOTTERY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"LOTTERY_CODE\" TEXT NOT NULL ," + // 1: lotteryCode
                "\"EXPECT\" TEXT NOT NULL ," + // 2: expect
                "\"CANDIDATE_CODE\" TEXT," + // 3: candidateCode
                "\"CANDIDATE_CODE2\" TEXT," + // 4: candidateCode2
                "\"REG_DATE\" INTEGER," + // 5: regDate
                "\"IS_WIN\" TEXT);"); // 6: isWin
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LT_LOTTERY\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, Lottery entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getLotteryCode());
        stmt.bindString(3, entity.getExpect());
 
        String candidateCode = entity.getCandidateCode();
        if (candidateCode != null) {
            stmt.bindString(4, candidateCode);
        }
 
        String candidateCode2 = entity.getCandidateCode2();
        if (candidateCode2 != null) {
            stmt.bindString(5, candidateCode2);
        }
 
        java.util.Date regDate = entity.getRegDate();
        if (regDate != null) {
            stmt.bindLong(6, regDate.getTime());
        }
 
        String isWin = entity.getIsWin();
        if (isWin != null) {
            stmt.bindString(7, isWin);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public Lottery readEntity(Cursor cursor, int offset) {
        Lottery entity = new Lottery( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // lotteryCode
            cursor.getString(offset + 2), // expect
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // candidateCode
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // candidateCode2
            cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)), // regDate
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6) // isWin
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, Lottery entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLotteryCode(cursor.getString(offset + 1));
        entity.setExpect(cursor.getString(offset + 2));
        entity.setCandidateCode(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setCandidateCode2(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRegDate(cursor.isNull(offset + 5) ? null : new java.util.Date(cursor.getLong(offset + 5)));
        entity.setIsWin(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(Lottery entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(Lottery entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
}
