package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenerateURlAndMethod {
    public final static String URLPREFIXPATTERN = "(?<=@RequestMapping\\(\").*(?=\".*)";
    public final static String URLPATTERN = "(?<=@RequestMapping\\(value = \").*(?=\".*)";
    public final static String METHOD = "(?<=public ResponseEntity ).*?(?=\\(.*)";
    public final static String METHDWITHPARAMETER = "(?<=public ResponseEntity )[\\s\\S]*?[^\"](?=\\{)";
    public final static String REQUESTMETHODPATTERN = "(?<=method = RequestMethod.).*(?=\\))";
    private static final HashMap<String, String> REPLACEALLPATTERN = new HashMap<String, String>() {
        /**
         */
        private static final long serialVersionUID = 4054658741830515853L;

        {
            put(" Integer ", " int? ");
            put("Date ", " string ");
            put("String ", "string ");
            put(" Boolean ", " bool? ");
            put(" Double ", " double? ");
            put(" Long ", " long? ");
            put("@PathVariable\\(.*?\\) ", "");
            put("@PathVariable ", "");
            put("@RequestParam\\(.*?\\) ", "");
            put("@RequestParam ", "");
            put("@RequestBody ", "");
            put(", HttpServletRequest .*?(?=,)", "");
            put("throws .*", "");
        }
    };

    public static void test() throws Exception {
        String URlPrefix = "";
        String methodString = "";
        String interfaceString = "";
        List<String> URLList = new ArrayList<>();
        List<String> MethodList = new ArrayList<>();
        List<String> MethodWithParam = new ArrayList<>();
        List<File> files = FileConvertUtil.getAllFiles("Controller.java", true, null);
        String totalString = "";
        String result = "";
        for (File file : files) {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String xString = null;
            while ((xString = bufferedReader.readLine()) != null) {
                System.out.println(xString + "\r\n");
                totalString += xString + "\r\n";
            }
            bufferedReader.close();
            inputStreamReader.close();
            fileInputStream.close();
            Pattern patternUrlPrefix = Pattern.compile(URLPREFIXPATTERN);
            Matcher matcherUrlPrefix = patternUrlPrefix.matcher(totalString);
            while (matcherUrlPrefix.find()) {
                URlPrefix = matcherUrlPrefix.group();
            }
            // 匹配URl
            Pattern patternUrl = Pattern.compile(URLPATTERN);
            Matcher matcherUrl = patternUrl.matcher(totalString);
            while (matcherUrl.find()) {
                URLList.add(matcherUrl.group());
            }
            // 匹配方法名
            Pattern patternMethod = Pattern.compile(METHOD);
            Matcher matcherMethod = patternMethod.matcher(totalString);
            while (matcherMethod.find()) {
                MethodList.add(matcherMethod.group());
            }
            // 匹配方法名包括参数
            Pattern patternMethodWithParam = Pattern.compile(METHDWITHPARAMETER);
            Matcher matcherMethodWithParam = patternMethodWithParam.matcher(totalString);
            while (matcherMethodWithParam.find()) {
                MethodWithParam.add(matcherMethodWithParam.group());
            }
            // 匹配请求方式
            List<String> RequestMethodList = new ArrayList<>();
            Pattern patternRequestMethod = Pattern.compile(REQUESTMETHODPATTERN);
            Matcher matcherRequestMethod = patternRequestMethod.matcher(totalString);
            while (matcherRequestMethod.find()) {
                RequestMethodList.add(matcherRequestMethod.group());
            }
            result += "\r\n#region " + URlPrefix + "\r\n\r\n";
            methodString += "\r\n#region " + URlPrefix + "\r\n\r\n";
            interfaceString += "\r\n#region " + URlPrefix + "\r\n\r\n";
            if (!(URLList.size() == MethodList.size())) {
                System.out.println(URlPrefix + "method number not match");
                break;
            }
            for (int i = 0; i < URLList.size(); i++) {
                /**
                 * 拼写方法string
                 */
                switch (RequestMethodList.get(i)) {
                    case "POST":
                        methodString += "public ResponseEntity "
                                + (new StringBuilder()).append(Character.toUpperCase(MethodWithParam.get(i).charAt(0)))
                                        .append(MethodWithParam.get(i).substring(1)).toString()
                                + ", string p_User){\r\n    string m_Uri = UnionUrl(c_" + (new StringBuilder())
                                        .append(Character.toUpperCase(MethodList.get(i).charAt(0))).append(MethodList.get(i).substring(1)).toString()
                                + "Url);\r\n    return WebRequestUtil.PostOperation<ResponseEntity>(m_Uri, p_User);\r\n}\r\n\r\n";

                        break;
                    case "GET":
                        methodString += "public ResponseEntity "
                                + (new StringBuilder()).append(Character.toUpperCase(MethodWithParam.get(i).charAt(0)))
                                        .append(MethodWithParam.get(i).substring(1)).toString()
                                + ", string p_User){\r\n    string m_Uri = UnionUrl(c_" + (new StringBuilder())
                                        .append(Character.toUpperCase(MethodList.get(i).charAt(0))).append(MethodList.get(i).substring(1)).toString()
                                + "Url);\r\n    return WebRequestUtil.GetSingleObjectOperation<ResponseEntity>(m_Uri,p_User);\r\n}\r\n\r\n";

                        break;
                    default:
                        methodString += "public ResponseEntity "
                                + (new StringBuilder()).append(Character.toUpperCase(MethodWithParam.get(i).charAt(0)))
                                        .append(MethodWithParam.get(i).substring(1)).toString()
                                + ", string p_User){\r\n    string m_Uri = UnionUrl(c_" + (new StringBuilder())
                                        .append(Character.toUpperCase(MethodList.get(i).charAt(0))).append(MethodList.get(i).substring(1)).toString()
                                + "Url);\r\n    return null;\r\n}\r\n\r\n";
                        break;
                }
                /**
                 * 拼写接口string
                 */
                interfaceString += "ResponseEntity " + (new StringBuilder()).append(Character.toUpperCase(MethodWithParam.get(i).charAt(0)))
                        .append(MethodWithParam.get(i).substring(1)).toString() + ", string p_User);\r\n\r\n";
                /**
                 * 拼写Url
                 */
                result += "private string c_"
                        + (new StringBuilder()).append(Character.toUpperCase(MethodList.get(i).charAt(0))).append(MethodList.get(i).substring(1)).toString()
                        + "Url = " + "\"mobile-front/" + URlPrefix + "/" + URLList.get(i) + "/\";" + "\r\n\r\n";
                System.out.println("string c_"
                        + (new StringBuilder()).append(Character.toUpperCase(MethodList.get(i).charAt(0))).append(MethodList.get(i).substring(1)).toString()
                        + "Url = " + "\"mobile-front/" + URlPrefix + "/" + URLList.get(i) + "/\"");
            }
            MethodList.clear();
            URLList.clear();
            totalString = "";
            result += "\r\n#endregion\r\n";
            methodString += "\r\n#endregion\r\n";
            interfaceString += "\r\n#endregion\r\n";
            for (Map.Entry<String, String> entry : REPLACEALLPATTERN.entrySet()) {
                Pattern patternReplace = Pattern.compile(entry.getKey());
                methodString = patternReplace.matcher(methodString).replaceAll(entry.getValue());
                interfaceString = patternReplace.matcher(interfaceString).replaceAll(entry.getValue());
            }
        }
        File m_File = new File("C:\\Users\\kira1210\\Documents\\bean\\" + "1.txt");
        FileOutputStream fileOutputStream = new FileOutputStream(m_File, false);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "utf-8");
        BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
        bufferedWriter.write(result + "\r\n" + methodString + "\r\n" + interfaceString);
        bufferedWriter.close();
        outputStreamWriter.close();
        fileOutputStream.close();
        System.out.println();
    }
}
