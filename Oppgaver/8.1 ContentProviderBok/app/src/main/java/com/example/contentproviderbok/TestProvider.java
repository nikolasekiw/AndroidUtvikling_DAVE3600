package com.example.contentproviderbok;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TestProvider extends ContentProvider {
    public static final String _ID = "_id";
    public static final String TITTEL = "Tittel";
    private static final String DB_NAVN = "bok.db";
    private static final int DB_VERSJON = 4;
    private final static String TABELL = "bok";
    public final static String PROVIDER = "com.example.contentproviderbok";
    private static final int BOK = 1; //sjekk på om noe skal gjøres med en eller alle poster (BOK og MBOK) ??
    private static final int MBOK = 2;
    TestProvider.DatabaseHelper DBhelper;
    SQLiteDatabase db;

    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER + "/bok"); //bestemmer path og provider selv. De andre må ha URI som ser likt ut
    private static final UriMatcher uriMatcher;

    /**
     * tar URI-en vår inn og sjekker om står noe etter bok, og hvis det gjør det så betyr
     * det at vi skal gjøre noe med en gitt id og da vet vi at det er sagt at det skal gjøres noe med en .
     */
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER, "bok", MBOK);
        uriMatcher.addURI(PROVIDER, "bok/#", BOK);
    }

    private static class DatabaseHelper extends SQLiteOpenHelper { //lagt den til her fordi den jobber med data i CP
        DatabaseHelper(Context context) { //konstruktør, sender med context, db navnet og versjon
        super(context, DB_NAVN, null, DB_VERSJON);
        }

        /**
         * Må ha to metoder i db. Hvis den ikke eksisterer vil den kjøre onCreate og opprette db.
         * Viktig at SQL-setningene er riktige. Skriver ut sql setningen for å være sikker på at alt
         * er bra. Sender SQL-setningn til slutt
         */
        @Override public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE " + TABELL + "("
                + _ID + " INTEGER PRIMARY KEY," + TITTEL + " TEXT);";
            Log.d("DatabaseHelper ", " oncreated sql " + sql);
            db.execSQL(sql);
        }

        /**
         * Blir kjørt hvis DB-nummeret endrer seg. Hvis versjonen endrer seg.
         * Dropper tabellen hvis den finnes
         */
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABELL);
            Log.d("DatabaseHelper", "updated");
            onCreate(db);
        }
    }

    /**
     * Denne returnerer boolsk variabel. Lager nytt dbhelper objekt og får tak i en skrivbar database
     * Når det gjeøres førse gang: vil kjøre db sin create
     */
    @Override
    public boolean onCreate() {
        DBhelper = new TestProvider.DatabaseHelper(getContext());
        db = DBhelper.getWritableDatabase();
        return true;
    }

    /**
     * Tar URI inn og sjekker på URI-matcher om det skal gjøres noe med en eller flere objekter
     * også bruker den noen ferdigdefinerte android ting som den sender tilbake. Hvis det er feil
     * URI så sender den tilbake at det er feil URI
     */
    @Override
    public String getType(Uri uri) {
        switch (uriMatcher.match(uri)) {
        case MBOK:
            return "vnd.android.cursor.dir/vnd.example.bok";
        case BOK:
            return "vnd.android.cursor.item/vnd.example.bok";
        default: throw new     IllegalArgumentException("Ugyldig URI" + uri);
        }
    }

    /**
     * RFunksjon som returnerer en verdi. Tar som parametere URI som er oppgitt og values, dvs et sett med nøkkel/verdi
     * par og det er fordi vi skal legge noe inn i db og vi må først sjekke uri og få takk i
     * dataene vi skal legge i db. Vi får tak i db så tar vi db.insert.... må så hvilke tabell
     * jeg skal legge inn i og hvilke verdier jeg skal legge inn. I dette tilfellet er det bare tittel
     * som skal legges inn. Det er det som kommer inn.
     *
     * Fordi det sal sendes tilbake URI så tar jeg spørring mot db og finner den siste IDøen som ligger
     * der også sender jeg tilbake den opprinnelige URI med id til den som er lagt inn i databasen som retur.
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = DBhelper.getWritableDatabase();
        db.insert(TABELL, null, values);
        Cursor c = db.query(TABELL, null, null, null, null, null, null); c.moveToLast();
        long minid = c.getLong(0);
        getContext().getContentResolver().notifyChange(uri, null);
        return ContentUris.withAppendedId(uri, minid);
    }

    /**
     * Tar URI-en inn også har den hvilke felt vi spør etter og om vi har noen spesielle utvelgelseskrav og hva
     * som er utvekslingskravene og hvordan det skal sorteres. Når vi utfører query, må vi vite hva det er som skal
     * hentes. Da bruker vi URI-matcher og sjekker om den returnerer et ett tall, dvs at den har et tall bakerst i
     * URI o ghvis den har det så skal jeg hente bare en post og da sender jeg query fra db, sier hvilken tabell jeg skal hente fra,
     * at utvgelsen er fra ID også får jeg tak i med URI get(1)... det tallet som er bakerst i URI og returnerer curser.
     * Hvis ikke så er det ikke noe tall bakerst i URL og da returnerer jeg alle verdier i db.
     * Ta null på resten av parameterne fordi jeg skal returnere alt.
     */
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cur = null;
        if (uriMatcher.match(uri) == BOK) {
            cur = db.query(TABELL, projection, _ID + "=" + uri.getPathSegments().get(1), selectionArgs, null, null, sortOrder);
            return cur; } else {
            cur = db.query(TABELL, null, null, null, null, null, null);
            return cur; }
    }

    /**
     *
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (uriMatcher.match(uri) == BOK) {
            db.update(TABELL, values, _ID + " = " + uri.getPathSegments().get(1), null);
            getContext().getContentResolver().notifyChange(uri, null);
            return 1;
        }
        if (uriMatcher.match(uri) == MBOK) {
            db.update(TABELL, null, null, null);
            getContext().getContentResolver().notifyChange(uri, null); return 2;
        }
        return 0; }

    /**
     *
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (uriMatcher.match(uri) == BOK) {
        db.delete(TABELL, _ID + " = " + uri.getPathSegments().get(1), selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return 1;
    }
        if (uriMatcher.match(uri) == MBOK) {
            db.delete(TABELL, null, null);
            getContext().getContentResolver().notifyChange(uri, null); return 2;
        }
        return 0; }
}
