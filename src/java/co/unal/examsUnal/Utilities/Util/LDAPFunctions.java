/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and opfen the template in the editor.
 */
package co.unal.examsUnal.Utilities.Util;

/**
 *
 * @author yeisondavid
 */
import com.novell.ldap.LDAPAttribute;
import com.novell.ldap.LDAPAttributeSet;
import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPEntry;
import com.novell.ldap.LDAPException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class LDAPFunctions {

    private LDAPConnection lc = new LDAPConnection();
    String idUser = "650";
    String idAdmin = "651";
    char base64Table[] = new char[64];

    private void startBase64Table()
    {
        int j = 0;
        for( int i = 0; i < 26; i++)base64Table[j++] = (char)('A'+i);
        for( int i = 0; i < 26; i++)base64Table[j++] = (char)('a'+i);
        for( int i = 0; i < 10; i++)base64Table[j++] = (char)('0'+i);
        base64Table[j++] = '+';
        base64Table[j++] = '/';
    }
    public LDAPFunctions()
    {
        startBase64Table();
    }
    public String login(String nombreUsuario, String contrasena){

        System.out.println("DATOS ---> " + nombreUsuario + " - " + contrasena);
        boolean connected = conectar();
        String result;
        if(connected){
            result = validarContrasena(nombreUsuario, contrasena);
            try {
                lc.disconnect();
            } catch (LDAPException ex) {
                System.out.println("Error discconecting LDAP server");
                Logger.getLogger(LDAPFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            result = "ErrorConnectingToLDAPServer";
        }
        return result;
    }


    public boolean addUser(String typeOfUser, String idAuthentication, String password)
    {
        /*attributes:
        uid: idAuthentication

        */
        LDAPAttributeSet attributeSet = new LDAPAttributeSet();
        if (typeOfUser.equals("user"))
        {
            attributeSet.add( new LDAPAttribute("homeDirectory", "/home/users/"+idAuthentication));
            attributeSet.add( new LDAPAttribute("gidNumber", idUser));
        }
        else
        {
            attributeSet.add( new LDAPAttribute("homeDirectory", "/home/admins/"+idAuthentication));
            attributeSet.add( new LDAPAttribute("gidNumber", idAdmin));
        }
        attributeSet.add( new LDAPAttribute("uid", idAuthentication));
        attributeSet.add( new LDAPAttribute("cn", idAuthentication));
        attributeSet.add( new LDAPAttribute("sn", "aSurname"));
        attributeSet.add( new LDAPAttribute("uidNumber", "1010")); //TODO
        attributeSet.add( new LDAPAttribute("givenName", "aName"));
        attributeSet.add( new LDAPAttribute("objectClass", new String[]{"inetOrgPerson","posixAccount","top"}));
        try {
            attributeSet.add( new LDAPAttribute("userPassword", "{MD5}"+hexToBase64( MD5(password))));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error in MD5 function");
            Logger.getLogger(LDAPFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        //String dn = "cn="+idAuthentication+",ou=examsUnal,dc=ocs,dc=com";
        String dn = "cn="+idAuthentication+",ou=examsUnal,dc=arqsoft,dc=com";
        LDAPEntry newEntry = new LDAPEntry( dn, attributeSet );
        boolean conected = conectar();
        if (conected)
        {
            try {
                lc.add(newEntry);
            } catch (LDAPException ex) {
                System.out.println("Error adding LDAP entry");
                Logger.getLogger(LDAPFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                lc.disconnect();
            } catch (LDAPException ex) {
                System.out.println("Error discconecting LDAP server");
                Logger.getLogger(LDAPFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
            //login(idAuthentication, password); // no lo quiten.
            return true;
        }
        return false;
    }
    private String MD5(String s) throws NoSuchAlgorithmException
    {
        MessageDigest m=MessageDigest.getInstance("MD5");
        m.update(s.getBytes(),0,s.length());
        return new BigInteger(1,m.digest()).toString(16);
    }

    private String hexToBase64(String s)
    {
        s = s.toUpperCase();
        String bitS = "";
        int aux;
        String auxS;
        for( int i = 0; i < s.length(); i++)
        {
            aux = Integer.parseInt(s.substring(i, i+1), 16);
            auxS = Integer.toBinaryString(aux);
            while( auxS.length() < 4) auxS = "0"+auxS;
            bitS += auxS;
        }
        int sigE = 0;
        while( bitS.length() % 6 != 0)
        {
            bitS += "00";
            sigE++;
        }
        String encoded="";
        for(int i = 0; i < bitS.length(); i+=6 )
        {
            encoded += base64Table[Integer.parseInt(bitS.substring(i, i+6), 2)];
        }
        for( int i = 0; i < sigE; i++ ) encoded += "=";
        return encoded;
    }
    private Boolean conectar(){

        //String ldapHost = "LDAP";
        String ldapHost = "20.0.2.23";
        //String dn = "cn=admin,dc=ocs,dc=com";
        String dn ="cn=admin,dc=arqsoft,dc=com";
        String password = "ArqSoft2016i";

        int ldapPort =  LDAPConnection.DEFAULT_PORT;
        int ldapVersion = LDAPConnection.LDAP_V3;

        try {
            lc.connect(ldapHost, ldapPort);
            System.out.println("====Conectado al Servidor LDAP====");
            lc.bind(ldapVersion, dn, password.getBytes("UTF8"));
            System.out.println("====Autenticado en el servidor====");
            return true;
        } catch (LDAPException | UnsupportedEncodingException ex) {
            System.out.println("====ERROR al conectarse al Servidor LDAP====");
            return false;
        }

    }

    private String validarContrasena(String nombreUsuario, String contrasena){

            //String dn = "cn="+nombreUsuario+",ou=examsUnal,dc=ocs,dc=com";
            String dn = "cn="+nombreUsuario+",ou=examsUnal,dc=arqsoft,dc=com";
        try {
            lc.bind(dn, contrasena);
            LDAPEntry myEntry = lc.read(dn);
            LDAPAttributeSet attributeSet = myEntry.getAttributeSet();

            System.out.println(attributeSet.toString());
            if (attributeSet.getAttribute("gidNumber").getStringValue().trim().equals(idUser))
                return "user";
            return "admin";
        } catch (LDAPException ex) {
            System.out.println("====ERROR al validar la contrasena====");
            return "unidentified";
        }

    }


}
