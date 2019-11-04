package cl.streamlink.contact.utils;

import net.minidev.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class MiscUtils {

    public static Pageable convertFromAngularPage(Pageable pageable, Sort.Direction dir, boolean isProject) {

        pageable = pageable.previousOrFirst();

        if (dir != null) {
            return PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    new Sort(dir, pageable.getSort().stream().map(order -> {

                        if (order.getProperty().equalsIgnoreCase("email1"))
                            return "contact.email1";

                        if (order.getProperty().equalsIgnoreCase("city"))
                            return "contact.city";


                        if (order.getProperty().equalsIgnoreCase("experience"))
                            return "skillsInformation.experience";

                        if (isProject && order.getProperty().equalsIgnoreCase("activityArea"))
                            return "projectInformation.activityArea";

                        if (isProject && order.getProperty().equalsIgnoreCase("client"))
                            return "societyContact.society.label";

                        return order.getProperty();
                    }).collect(Collectors.toList())));
        }
        return pageable;
    }

    public static JSONObject createSuccessfullyResult(String info) {
        JSONObject result = new JSONObject();
        result.put("result", "Operation Successfully Done");
        result.put("info", info);
        return result;
    }

    public static JSONObject createSuccessfullyResult() {
        JSONObject result = new JSONObject();
        result.put("result", "Operation Successfully Done");
        return result;
    }

    public static JSONObject createInProgressResult() {
        JSONObject result = new JSONObject();
        result.put("result", "Operation is in Progress ...");
        return result;
    }

    public static JSONObject createFailedResult(String msg) {
        JSONObject result = new JSONObject();
        result.put("result", "Operation Failed");
        result.put("info", msg);
        return result;
    }

    public static String generateReference() {
        return RandomStringUtils.randomNumeric(4);
    }


    public static boolean isNotEmpty(List<?> list) {
        return list != null && list.size() > 0;
    }

    public static boolean isNotEmpty(Object[] array) {
        return array != null && array.length > 0;
    }

    public static boolean isNotEmpty(String text) {
        return text != null && text.trim().length() > 0;
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return map != null && map.size() > 0;
    }

    public static boolean isEmpty(List<?> list) {
        return list == null || list.isEmpty();
    }

    public static boolean isEmpty(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static boolean isEmpty(Object[] array) {
        return array == null || array.length == 0;
    }

    public static boolean equals(Object object1, Object object2) {
        if (object1 == null)
            return object2 == null;
        else if (object2 == null)
            return false;
        return Objects.equals(object1, object2);
    }

}
