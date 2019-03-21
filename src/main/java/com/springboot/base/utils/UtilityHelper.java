package com.springboot.base.utils;//package com.classroomhunt.classroomhuntbackend.utils;

import org.glassfish.jersey.server.validation.ValidationError;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

public class UtilityHelper {

    public static String generateErrorMessage(Map<String, List<ValidationError>> errorsAll) {
        String errorMsg = "";
        int size = errorsAll.size();
        int count = 0;
        for (String field : errorsAll.keySet()) {
            errorMsg += field + " ";
            for (ValidationError error : errorsAll.get(field)) {
                if (size - count == 1) {
                    errorMsg += error.toString();
                } else {
                    errorMsg += error.toString() + ", ";
                }
            }
            count++;
        }
        return errorMsg;
    }

    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }

    public static String getSha512(String value) {
        try {
            return new String(MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8")));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

//    public static String getTokenFromHeader(Http.Context ctx) {
//        String[] authTokenHeaderValues = ctx.request().headers().get(AUTH_TOKEN_HEADER);
//        if ((authTokenHeaderValues != null) && (authTokenHeaderValues.length == 1) && (authTokenHeaderValues[0] != null)) {
//            return authTokenHeaderValues[0];
//        }
//        return null;
//    }
//
//    public static String removeEndComma(String text) {
//        if (!isEmpty(text) && text.charAt(text.length() - 1) == ',') {
//            text = StringUtils.substring(text, 0, -1);
//        }
//        return !isEmpty(text) ? text.trim() : text;
//    }
//
//    public static Result sendResponse(Object data, JsonMetaContent jsonMetaContent, Http.Context ctx) {
//        if (jsonMetaContent == null) return Results.badRequest();
//        try {
//            if (ctx.args.containsKey(START_TIME) && ctx.args.get(START_TIME) != null) {
//                long startTime = (long) ctx.args.get(START_TIME);
//                long endTime = Calendar.getInstance().getTimeInMillis();
//                jsonMetaContent.setExecTime((double) (Math.abs(endTime - startTime)) / 1000 + " sec");
//            }
//            jsonMetaContent.setVersion(ctx.messages().at("version"));
//            HTTPJSONResponse<Object> httpjsonResponse = new HTTPJSONResponse<>(data, jsonMetaContent);
//            return status(jsonMetaContent.getHttpStatus(), Json.toJson(httpjsonResponse));
//        } catch (Exception e) {
//            Logger.error(data.getClass().getSimpleName(), e);
//            return Results.badRequest();
//        }
//    }
//
//    public static String generateErrorMessage(Http.Context context, String type) {
//        if (context == null || isEmpty(type)) return SOME_WRONG;
//        switch (type) {
//            case Constants.ERROR_TOKEN_EXPIRED:
//                return context.messages().at("token.expired");
//            case Constants.ERROR_ACC_DELETED:
//                return context.messages().at("account.deleted");
//            case Constants.ERROR_ACC_DISABLED:
//                return context.messages().at("account.disabled");
//            case Constants.ERROR_AUTH_FAIL:
//                return context.messages().at("unauthorized");
//            case Constants.ERROR_NO_AUTH:
//                return context.messages().at("auth.fail");
//            default:
//                return context.messages().at("auth.fail");
//        }
//    }
//
//    public static boolean hasUser(Http.Context ctx) {
//        return ctx.args.containsKey(AUTH_TOKEN_HEADER) && ctx.args.get(AUTH_TOKEN_HEADER) != null;
//    }

//    //only admin can get list of users
//    //noOrgs ->to avoid huge nesting of orgs<->users due to mapping
//    public static List<JsonUserResponse> convertUsersListJson(Collection<User> users) {
//        List<JsonUserResponse> jsonUserResponses = new LinkedList<>();
//        if (users == null || users.isEmpty()) return jsonUserResponses;
//        for (User user : users) {
//            JsonUserResponse jsonUserResponse = new JsonUserResponse(user, getUserType(user), true, true);
//            // jsonUserResponse.setUserType(null);//no need to send type as list of user->roles will be displayed
//            jsonUserResponses.add(jsonUserResponse);
//        }
//        return jsonUserResponses;
//    }
//
//    public static String getUserType(User user) {
//        if (user == null || user.getRoles() == null || user.getRoles().isEmpty()) return TYPE_USER;
//        for (Role role : user.getRoles()) {
//            if (role.getRoleName().equals(ROLE_SUPER_ADMIN))
//                return TYPE_ADMIN;
//            if (role.getRoleName().equals(ROLE_SCHOOL_ADMIN))
//                return TYPE_SCHOOL_ADMIN;
//            if (role.getRoleName().equals(ROLE_USER))
//                return TYPE_USER;
//        }
//        return TYPE_USER;
//    }

//    public static List<JsonOrgResponse> convertOrgsListJson(Collection<Organisation> orgs, User user) {
//        List<JsonOrgResponse> jsonOrgResponse = new LinkedList<>();
//        if (orgs == null || orgs.isEmpty()) return jsonOrgResponse;
//        boolean isAdmin = isAdminByRole(user);
//        for (Organisation org : orgs) {
//            if (!isAdmin && (!org.isEnabled() || org.isDeleted())) {
//                //normal user cant see org when it is disabled or deleted
//            } else {
//                if (org.getId() != 1) {//ignore dummy org
//                    boolean isSchoolAdmin = user != null && user.getOrganisations() != null && user.getOrganisations().contains(org);
//                    boolean isFav = user != null && user.getFavOrganisations() != null && user.getFavOrganisations().contains(org);
//                    JsonOrgResponse jsonOrg = new JsonOrgResponse(org, isAdmin, true, true, isSchoolAdmin, isFav);
//                    jsonOrgResponse.add(jsonOrg);
//                }
//            }
//        }
//        return jsonOrgResponse;
    }

    //check admin by looping user roles
//    public static boolean isAdminByRole(User user) {
//        if (user == null) return false;
//        if (user.getRoles() == null || user.getRoles().isEmpty()) return false;
//        for (Role role : user.getRoles()) {
//            if (role.getRoleName().equals(ROLE_SUPER_ADMIN)) return true;
//        }
//        return false;
//    }

//    public static double delta(double d1, double d2) {
//        return Math.abs(d1 - d2) / Math.max(Math.abs(d1), Math.abs(d2));
//     }

//    public static HTTPJSONResponse getErrorJson(final Http.Context context, String type) {
//        String message = generateErrorMessage(context, type);
//        JsonMetaContent jsonMetaContent = new JsonMetaContent<>(UNAUTHORIZED, message, null, null, 0, null);
//        if (context.args.containsKey(START_TIME) && context.args.get(START_TIME) != null) {
//            long startTime = (long) context.args.get(START_TIME);
//            long endTime = Calendar.getInstance().getTimeInMillis();
//            jsonMetaContent.setExecTime((double) (Math.abs(endTime - startTime)) / 1000 + " sec");
//        }
//        jsonMetaContent.setVersion(context.messages().at("version"));
//        return new HTTPJSONResponse<>(null, jsonMetaContent);
//    }


//    //check school admin by looping user roles
//    public static boolean isSchoolAdminByRole(User user) {
//        if (user == null || isEmpty(user.getAuthToken())) return false;
//        if (user.getRoles() == null || user.getRoles().isEmpty()) return false;
//        for (Role role : user.getRoles()) {
//            if (role.getRoleName().equals(ROLE_SCHOOL_ADMIN)) return true;
//        }
//        return false;
//    }
//
//    //check user has given org->true if he is school admin of given org else false
//    public static boolean isSchoolAdmin(User user, Organisation organisation) {
//        if (user == null || user.getOrganisations() == null || user.getOrganisations().isEmpty() || organisation == null)
//            return false;
//        for (Organisation org : user.getOrganisations()) {
//            if (org.equals(organisation)) return true;
//        }
//        return false;
//    }

    //helper method to process upload org csv row
//    public static JsonSUOrgForm processRow(String[] row) {
//        JsonSUOrgForm jsonSUOrgForm = new JsonSUOrgForm();
//        try {
//            if (row == null || row.length != 17) return null;
//            //below indexes are compulsory for org creation
//            if (isEmpty(row[0]) || isEmpty(row[1]) || isEmpty(row[2])) {
//                return null;
//            }
//     //       jsonSUOrgForm.setName(row[0]);
//            try {
//            //    jsonSUOrgForm.setLocationID(Long.parseLong(row[1]));
//            } catch (Exception e) {
//               // Logger.error("processRow " + row[0] + " ", e);
//                return null;
//            }
//            try {
//                List<Long> filters = new ObjectMapper().readValue(row[2]
//                        , TypeFactory.defaultInstance().constructCollectionType(List.class,
//                                Long.class));
//          //      jsonSUOrgForm.setFilters(filters);
//            } catch (Exception e) {
//              //  Logger.error("processRow " + row[0] + " ", e);
//                return null;
//            }
//        //    jsonSUOrgForm.setAddress(row[3]);
//        //    jsonSUOrgForm.setPhoneNumber(row[4]);
//        //    jsonSUOrgForm.setFaxNumber(row[5]);
//            OrganisationExtra extra = new OrganisationExtra();
//            extra.setAboutUs(row[6]);
//            extra.setExtraCurricular(row[7]);
//            extra.setEstablishedYear(row[8]);
//            if (!isEmpty(row[9])) {
//                extra.setMaxStudentsPerClass(Integer.parseInt(row[9]));
//            }
//            if (!isEmpty(row[10])) {
//                extra.setTotalStudents(Integer.parseInt(row[10]));
//            }
//            extra.setWebsite(row[11]);
//            extra.setContactEmail(row[12]);
//            if (!isEmpty(row[13])) {
//                extra.setLatitude(Double.parseDouble(row[13]));
//            }
//            if (!isEmpty(row[14])) {
//                extra.setLongitude(Double.parseDouble(row[14]));
//            }
//            //extra.setAdmissionStartDate();
//            // extra.setAdmissionEndDate();
//         //   jsonSUOrgForm.setExtra(extra);
//        } catch (Exception e) {
//           // Logger.error("processRow " + row[0] + " ", e);
//            return null;
//        }
//        return jsonSUOrgForm;
//    }

//    public static <T> List<List<T>> getPages(Collection<T> c, Integer size) {
//        if (c == null)
//            return Collections.emptyList();
//        List<T> list = new ArrayList<>(c);
//        if (size == null || size <= 0 || size > list.size())
//            size = list.size();
//        int numPages = (int) Math.ceil((double) list.size() / (double) size);
//        List<List<T>> pages = new ArrayList<>(numPages);
//        for (int pageNum = 0; pageNum < numPages; )
//            pages.add(list.subList(pageNum * size, Math.min(++pageNum * size, list.size())));
//        return pages;
//    }
//
//    public static <T> List<T> getPage(Collection<T> sourceList, int page, int size) {
//        if (size <= 0 || page <= 0) {
//            size = MAX_PAGE_SIZE;
//            page = 1;
//        }
//        List<T> processList = new ArrayList<>();
//        int fromIndex = (page - 1) * size;
//        if (sourceList == null || sourceList.size() < fromIndex) {
//            return Collections.emptyList();
//        } else {
//            processList.addAll(sourceList);
//        }
//        // toIndex exclusive
//        return processList.subList(fromIndex, Math.min(fromIndex + size, processList.size()));
//    }
//
//}
