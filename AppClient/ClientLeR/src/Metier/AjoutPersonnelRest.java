/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Metier;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pauline
 */
public class AjoutPersonnelRest {

    public static void ajouterPersonnel(String banque, String nom, String motdepasse, String role) {
        String roleEnum;
        if (role.equals("Employé")) {
            roleEnum = "Employe";
        } else {
            roleEnum = "Gerant";
        }
        Gson gson = new Gson();
        HashMap<String, String> jsonArgs = new HashMap<String, String>();
        jsonArgs.put("banque", banque);
        jsonArgs.put("nom", nom);
        jsonArgs.put("motdepasse", motdepasse);
        jsonArgs.put("role", role);

        String maChaineJson; //Transformation en chaine de caractère
        maChaineJson = gson.toJson(jsonArgs);

        URL url;
        try {
            url = new URL("http://localhost:8001/Banque-1.0/banque/personnel/creer"); // remplacez l'Url

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Allow", "POST");
            OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
            out.write(maChaineJson);
            out.flush();
            out.close();
            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String response = br.readLine();
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
