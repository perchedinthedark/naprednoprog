package konstante;

/**
 * Enum StatusServerskogOdgovora definiše moguće statuse odgovora sa serverske strane 
 * nakon izvršenja operacija. Koristi se za označavanje da li je operacija uspešno 
 * izvršena ili je došlo do greške.
 * 
 * Moguće vrednosti:
 * - Success: Operacija je uspešno izvršena.
 * - Error: Došlo je do greške pri izvršenju operacije.
 * 
 * @author Ranko
 */
public enum StatusServerskogOdgovora {

    /** Status koji označava uspešno izvršenu operaciju */
    Success,

    /** Status koji označava da je došlo do greške */
    Error
}

