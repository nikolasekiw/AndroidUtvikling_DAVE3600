package com.example.merfragmenter;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class ListeFragment extends Fragment {
    private static ArrayAdapter<String> adapter;
    private static UrlEndret listener;

    public interface UrlEndret{ //da skal den kalle på en metode linkEndret. Interface: betyr at denne koden (linkEndret) ligger i aktiviteten under. Dvs. at aktiviteten under implementerer dette interfacet, den må håndtere at det klikkes på en link her og skal sende med hva det er klikket på i lista vår. Data går alltid fra fragmentet og ned til den umderliggende aktiviteten og de ter den som gjør forretningslogikken
        public void linkEndret(String link);
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context); //en av første metodene i livsløpet til et fragment
        Activity activity; //får tak i aktiviteten i context, dvs den underliggende aktiviteten
        activity = (Activity) context;
        try { //sjekker om den har implementert lytteren vår. Sjekker at aktiviteten under har implementert interfacet
            listener = (UrlEndret) activity;
            System.out.println("satt lytter");
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString()+" Må implementere UrlEndret");
        }
    }
    public ListeFragment(){} //denne konstruktøren gjør ikke noe

    //onCreateView vil sende med noen parametre som vi ike sender med. Inflate sender xml fil og lager noe ut av det. Container er lauouten vi legger det i. I bundle er hvis de te rlagret nøkkel/verdi par som skal sendes med
    @Override
    //det er denne metoden som gjør at vi kan lese fra xml filene våre
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceBundle){
        View v = inflater.inflate(R.layout.liste_layout, container, false); //bruker inflater til å lage view. Leser siste layouten vår, putter den i layputen til aktiviteten
        ListView lv = (ListView) v.findViewById(R.id.liste); //finner listview-et mitt som jeg laget (id) i layout filen til dette fragmentet
        String[] values = new String[]{"script1.txt", "script2.txt"}; //lager array av scriptfilene. Navnene på filene jeg skal liste i listview står også her
        final ArrayList<String> list = new ArrayList<>(); //legger filnavnene i en arraylist. Kobler arrayadapteren min til arraylisten
        for(int i = 0; i < values.length; i++){
            list.add(values[i]);
        }
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), //lager arrayobjektet og den trenge rcontecten
                android.R.layout.simple_list_item_1, list); //simple.. ferdig definert, bruker verdien rett i lista. List: kobler array til arrayAdapteren
        lv.setAdapter(adapter); //kobla
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) { //lager hva som sklal sjke når noen klikke på noe i lista
                String data = adapter.getItem(i);
                listener.linkEndret(data); //da kalles denne metoden. Dataene som sendes med er det jeg har klikket på i lista. Da sendes det ned til hovedaktiviteten som har linkEndret
            }
        });
        return v;
    }
}
