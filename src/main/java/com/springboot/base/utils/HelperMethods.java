package com.springboot.base.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.springframework.util.StringUtils.isEmpty;

/**
 * @author anuragdhunna
 */
public class HelperMethods {

    public static String generateMD5value(String text) {

        text = text+generateRandomString();
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert messageDigest != null;
        messageDigest.reset();
        messageDigest.update(text.getBytes());
        byte[] digest = messageDigest.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashtext = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while(hashtext.length() < 32 ){
            hashtext = "{MD5}"+hashtext;
        }
        System.out.println("{MD5}"+hashtext);
        return "{MD5}"+hashtext;
    }

    public static String generateRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

//    public static String generateSchoolAdminPassword(String email,Long id){
//        email=email.substring(0,email.indexOf('@'));
//        return id+email;
//    }
//
//    public static String removeEndComma(String text) {
//        if (!isEmpty(text) && text.charAt(text.length() - 1) == ',') {
//            text = StringUtils.substring(text, 0, -1);
//        }
//        return !isEmpty(text) ? text.trim() : text;
//    }

    //helper method to split filter ids and return list of those filter objects
    //return null if error occurs otherwise list of filters(can be empty)
    public static List<String> getListStringIds(String text) {
        List<String> filters = new ArrayList<>();
        if (isEmpty(text)) return filters;
        text = text.trim();
        try {
            if (text.contains(",")) {
                String[] filtList = text.split(",");
                for (String s : filtList) {
                    if (isEmpty(s)) continue;
                    filters.add(s);
                }
            } else {
                filters.add(text);
            }
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
        return filters;
    }

    public static List<Long> getFilterIDs(String text) {
        List<Long> filters = new ArrayList<>();
        if (isEmpty(text)) return filters;
        text = text.trim();
        try {
            if (text.contains(",")) {
                String[] filtList = text.split(",");
                for (String s : filtList) {
                    if (isEmpty(s)) continue;
                    Long filterID = Long.parseLong(s);
                    filters.add(filterID);
                }
            } else {
                Long filterID = Long.parseLong(text);
                filters.add(filterID);
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return filters;
    }

//    public static void runDataImportHandler() throws Exception {
//
//        SolrClient server = new HttpSolrClient(DATA_IMPORT_URL);
//        ModifiableSolrParams params = new ModifiableSolrParams();
//        params.set("command", "full-import");
//        QueryRequest request = new QueryRequest(params);
//        request.setPath("/datahandler");
//        server.request(request);
//    }

}
