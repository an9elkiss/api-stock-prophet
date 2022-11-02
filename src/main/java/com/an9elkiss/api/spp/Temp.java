package com.an9elkiss.api.spp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Temp {

//    private static final Pattern URL_REGEX2 = Pattern
//            .compile(
//                    "((?:(?:[a-z][-a-z0-9\\+\\.]*:)?\\/\\/)?(?:(?:(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:25[0-5]|[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+))(?::[0-9]*)?)"
//            +                             "(?:(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))*)*" +
//                            "|\\/(?:(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))+)(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))*)*)?" +
//                            "|(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))+)" +
//                            "(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))*)*" +
//                            "|(?!(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@])))" +
//
//                            "(?:\\?(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@])|[\\x{E000}-\\x{F8FF}\\x{F0000}-\\x{FFFFD}|\\x{100000}-\\x{10FFFD}\\/\\?])*)?" +
//                            "(?:\\#(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@])|[\\/\\?])*)?"
//
//            );

    private static final Pattern URL_REGEX3 = Pattern
            .compile(
                    "(?i)(((?:[a-z][-a-z0-9\\+\\.]*:)?\\/\\/)?" + // protocol //1 //6
//                            "(" + //2
                            "(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:])*@)?" + // auth
                            "([a-zA-Z0-9][-a-zA-Z0-9]{0,62}(?:\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+)" + // host/ip // 3
                            "(?::([0-9]*))?)"+ // port

                            "((?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))*)*" + //4
                            "|\\/(?:(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))+)(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))*)*)?" +
                            "|(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))+)(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))*)*)" +
//                            "|(?!(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@]))" + //5
//                            ")" +
                            "(?:\\?(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@])|[\\/\\?])*)?" +
                            "(?:\\#(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:@])|[\\/\\?])*)?"
            );

//    private Pattern URL_REGEX_ORG = Pattern
//            .compile(
//                    "(?i)([a-z](?:[-a-z0-9\\+\\.])*)" + // protocol
//                            ":(?:\\/\\/" +
//                            "(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:])*@)?" + // auth
//                            "((?:" +
//                            "\\[(?:(?:(?:[0-9a-f]{1,4}:){6}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|::(?:[0-9a-f]{1,4}:){5}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:){4}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:[0-9a-f]{1,4}:[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:){3}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,2}[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:){2}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,3}[0-9a-f]{1,4})?::[0-9a-f]{1,4}:(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,4}[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,5}[0-9a-f]{1,4})?::[0-9a-f]{1,4}|(?:(?:[0-9a-f]{1,4}:){0,6}[0-9a-f]{1,4})?::)|v[0-9a-f]+[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:]+)\\]" +
//                            "|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}" +
//                            "|(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=@])*" +
//                            "))" + // host/ip
//                            "(?::([0-9]*))?" + // port
//                            "(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))*)*" +
//                            "|\\/(?:(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))+)(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))*)*)?" +
//                            "|(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))+)(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))*)*" +
//                            "|(?!(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@])))" +
//                            "(?:\\?(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@])|[\\x{E000}-\\x{F8FF}\\x{F0000}-\\x{FFFFD}|\\x{100000}-\\x{10FFFD}\\/\\?])*)?" +
//                            "(?:\\#(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@])|[\\/\\?])*)?"
//            );

    private static final String REG_PART = "\\x{A0}-\\x{D7FF}\\x{F900}-\\x{FDCF}\\x{FDF0}-\\x{FFEF}\\x{10000}-\\x{1FFFD}\\x{20000}-\\x{2FFFD}\\x{30000}-\\x{3FFFD}\\x{40000}-\\x{4FFFD}\\x{50000}-\\x{5FFFD}\\x{60000}-\\x{6FFFD}\\x{70000}-\\x{7FFFD}\\x{80000}-\\x{8FFFD}\\x{90000}-\\x{9FFFD}\\x{A0000}-\\x{AFFFD}\\x{B0000}-\\x{BFFFD}\\x{C0000}-\\x{CFFFD}\\x{D0000}-\\x{DFFFD}\\x{E1000}-\\x{EFFFD}";

    private static Pattern URL_REGEX;

    static {
        URL_REGEX = Pattern
                .compile(
                        "(?i)(((?:[a-z][-a-z0-9\\+\\.]*:)?\\/\\/)?" + // protocol
                                "(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:])*@)?" + // auth
                                "((?:" +
                                "\\[(?:(?:(?:[0-9a-f]{1,4}:){6}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|::(?:[0-9a-f]{1,4}:){5}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:){4}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:[0-9a-f]{1,4}:[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:){3}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,2}[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:){2}(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,3}[0-9a-f]{1,4})?::[0-9a-f]{1,4}:(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,4}[0-9a-f]{1,4})?::(?:[0-9a-f]{1,4}:[0-9a-f]{1,4}|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3})|(?:(?:[0-9a-f]{1,4}:){0,5}[0-9a-f]{1,4})?::[0-9a-f]{1,4}|(?:(?:[0-9a-f]{1,4}:){0,6}[0-9a-f]{1,4})?::)|v[0-9a-f]+[-a-z0-9\\._~!\\$&'\\(\\)\\*\\+,;=:]+)\\]" +
                                "|(?:[0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(?:\\.(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9][0-9]|[0-9])){3}" +
                                "|(?:%[0-9a-f][0-9a-f]|[a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=@])(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=@])+" +
                                "))" + // host/ip
                                "(?::([0-9]*))?)" + // port

                                "((?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))*)*" +
                                "|\\/(?:(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))+)(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))*)*)?" +
                                "|(?:(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))+)(?:\\/(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@]))*)*)" +

                                "(?:\\?(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@])|[\\x{E000}-\\x{F8FF}\\x{F0000}-\\x{FFFFD}|\\x{100000}-\\x{10FFFD}\\/\\?])*)?" +
                                "(?:\\#(?:(?:%[0-9a-f][0-9a-f]|[-a-z0-9\\._~"+ REG_PART +"!\\$&'\\(\\)\\*\\+,;=:@])|[\\/\\?])*)?"
                );
    }

    public static void main(String[] args) {

        String x = "<p>http://www.abc.com/aaa</p><p>http://a.b.c:333?bbb</p>" +
                "<p>//a.b.c:333#ccc</p><p>//a.b.c:333/</p><p>//a.b.c:333/aaa</p>" +
                "<p>aaa.b.c:333/aaa</p>" +
                "<p>bbb.b.c</p>" +
                "xyzhttp://192.3.125.255:333aaabbbaaabbb" +
                "https://www.google.com:123/complete/search?q=java%20%E6%A0%87%E6%B3%A8%20%E5%AD%97%E6%AE%B5%E7%B1%BB%E5%9E%8B&cp=0&client=desktop-gws-wiz-on-focus-serp&xssi=t&hl=zh-CN&authuser=0&pq=java%20%E6%A0%87%E6%B3%A8%20%E5%AD%97%E6%AE%B5%E7%B1%BB%E5%9E%8B&psi=CNhgY7jvO5eAxc8P4dy7-Ao.1667291146653&ofp=EAEyyAEKDAoKamF2YeazqOinowoVChNqYXZh6Ieq5a6a5LmJ5rOo6KejChIKEGphdmHms6jop6Plrp7njrAKEgoQamF2YeazqOino-S9v-eUqAoRCg9KYXZhIGFubm90YXRpb24KHgocamF2YeazqOino-aYr-WmguS9leW3peS9nOeahAoPCg1AdGFyZ2V05rOo6KejCg4KDEphdmE4IOazqOinowoSChBqYXZh5rOo6Kej5pWw57uECg8KDeazqOinoyDmjqXlj6MQRw&newwindow=1&dpr=1";
        System.out.println(URL_REGEX);

        Matcher regexpMatcher = URL_REGEX.matcher(x);
        while (regexpMatcher.find()) {
            System.out.println(regexpMatcher.group(0));
            System.out.println(regexpMatcher.group(1));
            System.out.println(regexpMatcher.group(2));
            System.out.println(regexpMatcher.group(3));
            System.out.println("-----------------------------");


        }

    }
}
