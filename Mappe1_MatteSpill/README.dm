# Oppgavetekst

Dere skal lage en matteopplærings-app. Når applikasjonen startes skal man komme til et skjermbilde hvor mulige valg er Start spill, Se statistikk, Preferanser. Selve spillet viser addisjons regnestykker og knapper med tallene 0 til 9. Man skriver inn svar med knappene. Regnestykkene og svarene skal lagres som arrays i xml-fil.

Det skal legges inn 25 regnestykker. Regnestykkene skal vises random når et spill er startet. Samme regnestykke skal ikke komme opp flere ganger i en sesjon. Antall riktige og gale svar skal vises på skjermen når det spilles. Standard er at et spill er 5 regnestykker, men dette kan settes til 5, 10 eller 25 i Preferanser. Antall riktige og gale svar skal summeres opp og lagres slik at man neste gang kan gå inn på Statistikk og se disse.Det skal også være mulig å slette tidligere statistikk.

Hvis spilleren avbryter spillet før det er ferdig skal det komme opp en dialogboks som spør om det virkelig skal avsluttes. Avsluttede spill tas ikke med i statistikken. Hvis alle spørsmål er benyttet skal det komme en melding til brukeren om at det ikke er flere tilgjengelige spørsmål.

Spillet skal ha ulik layout i stående og liggende modus. Skjermbildet skal benyttes på en så god måte som mulig og designvalg bør følge designregler fra developer.android.com. Husk å lag eget ikon til appen på desktop og skap gjenkjennelse gjennom alle skjermbilder.

Alle strenger skal ligge i strings.xml. I Preferanser skal man kunne velge mellom språkene norsk og tysk. Ved endring av språk skal alle strenger i spillet skifte til riktige verdier.

Dere velger selv hvordan navigasjon mellom start-aktivitet og spill-aktivitet foregår, men pass på at ikke aktivitetene deres legger seg på stack.

Fint om tilstanden bevares når emulator roteres.

Det skal leveres med en rapport som skal ligge under res/raw folderen. Husk at filnavn må ha små bokstaver. Rapporten skal være i pdf-format navngis med studentnummer og vise skjermbildene og gangen i applikasjonen deres + begrunnelse for designvalg som farger, bruk av ikoner, navigasjonsmuligheteretc.

Minimum versjon android 6API23. Emulator som benyttes er Nexus 5 API_23.

NB! Prosjektnavnet SKAL være <studentnr>mappe1+domenet dere velger

Det blir lagt vekt på:
- Tilfredstille kravspek (20%) 
- Design (25%)
- Struktur kode (40%) 
- Rapport (15%)
