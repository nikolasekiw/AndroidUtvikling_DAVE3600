package com.example.merfragmenter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import androidx.fragment.app.Fragment;
import java.io.File;

public class VisScriptFragment extends Fragment { //det er fragment derfor extends fragment
    private String scriptnavn; //det er navnet på scriptet som skal vises

    public void init(String navn){ //tar med navnet inn og setter scriptnavnet på scriptet som skal vises.
        scriptnavn = navn;
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState){
        super.onActivityCreated(saveInstanceState);
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View scriptvindu = inflater.inflate(R.layout.visfil_layout, container, false); //bruker inflater og leser i visfillayout som vi ser har definert, sender med hvor denne layouten legges. Hvis den har klart å lage view så skal den finne webview i vievet og det kalte jeg visScript i xml
        if(scriptvindu != null){
            WebView script = (WebView) scriptvindu.findViewById(R.id.visscript);
            File imgFile = new File("file:///android_asset/"+scriptnavn);
            script.loadUrl("file:///android_asset/"+scriptnavn); //android_asset, hvis vi har prosjekt uten xml men noe vi ønsler prorammet skal ha tilgang til så legger vi det i en folkder som ligger i asset som ligger i samme nivå som ressurs.
            //når file:/// så er det samme som http//, definert hvordan uri er bygd opp. Når android_assets så leter etter assets katalogen. Der må det ligge fil som heter det scriptnavnet som er sendt inn
            //det scriptnavnet er script1sql og script2sql.
        }
        return scriptvindu;
    }

    public void updateUrl(String navn){ //når det er klikket på en annen link
        scriptnavn = navn; //får tak i view url og får tak i nye fila.
        WebView mittScript = (WebView) getView().findViewById(R.id.visscript);
        mittScript.loadUrl("file:///android_asset/"+scriptnavn);
    }
}
