package konstante;

/**
 * Interfejs Operacije sadrži sve operacije koje se koriste za upravljanje sistemom, 
 * kao što su login, upravljanje muzičarima, koncertima, sponzorima, binama, 
 * lokacijama i opremom. Svaka operacija je predstavljena konstantom koja se koristi 
 * kao indikator pri pozivanju specifičnih operacija na serverskoj strani.
 * 
 * Operacije su definisane kao celobrojne konstante.
 * 
 * @author Ranko
 */
public interface Operacije {

    /** Operacija za prijavu korisnika u sistem (LOGIN) */
    public static final int LOGIN = 0;

    /** Operacija za dodavanje novog muzičara u sistem */
    public static final int DODAJ_MUZICARA = 1;

    /** Operacija za brisanje postojećeg muzičara iz sistema */
    public static final int OBRISI_MUZICARA = 2;

    /** Operacija za izmenu podataka o muzičaru */
    public static final int IZMENI_MUZICARA = 3;

    /** Operacija za vraćanje liste svih muzičara */
    public static final int VRATI_MUZICARE = 4;

    /** Operacija za dodavanje novog koncerta u sistem */
    public static final int DODAJ_KONCERT = 5;

    /** Operacija za brisanje postojećeg koncerta iz sistema */
    public static final int OBRISI_KONCERT = 6;

    /** Operacija za izmenu podataka o koncertu */
    public static final int IZMENI_KONCERT = 7;

    /** Operacija za vraćanje liste svih koncerata */
    public static final int VRATI_KONCERTE = 8;

    /** Operacija za vraćanje liste svih izvođača */
    public static final int VRATI_IZVODJACE = 9;

    /** Operacija za vraćanje liste svih sponzora */
    public static final int VRATI_SPONZORE = 10;
    
    /** Operacija za vraćanje liste svih bina */
    public static final int VRATI_BINE = 11;

    /** Operacija za vraćanje liste svih lokacija */
    public static final int VRATI_LOKACIJE = 12;

    /** Operacija za vraćanje liste sve opreme */
    public static final int VRATI_OPREME = 13;

}