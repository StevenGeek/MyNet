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
import java.util.regex.Pattern;

import javax.swing.JFileChooser;

public class FileConvertUtil {
    private static String NameSpace = "GriffinReedMobileFrontProxy";
    private static String outputLocation = "D:\\Document\\Java\\classConversionOutput\\";
    private static final HashMap<String, String> J2DONETREPLACEAllMAP = new HashMap<String, String>() {
        {
            put("/\\*\\*[\\s]+\\*(.*)[\\s]+\\*/", "/// <summary>\r\n    ///$1\r\n    /// </summary>");
            put("/\\*\\*[\\s\\S]*?\\*/", "");
            put("private( .*);", "public$1 {get;set;}");
            put("[\r\n]*.*[@Override]*\r\n*.*public .*\\(.*\\).*\\{[^\\}]*;[\\r\\n]*.*\\}\r\n*", "");
            put(".*package.*", "using System;\r\nusing System.Collections.Generic;\r\nusing System.Linq;\r\nusing System.Text;");
            put("(.*public class.*)( implements Serializable)(.*)", "$1$3");
            put("(.*public) final( class.*)( implements Serializable)(.*)", "$1$2$4");
            put(" Integer ", " int? ");
            put(" Date ", " long? ");
            put(" String ", " string ");
            put(" extends ", " : ");
            put(" Boolean ", " bool? ");
            put(" Double ", " double? ");
            put(" Long ", " long? ");
            // put("//(.*)", "/// <summary>\r\n ///$1\r\n /// </summary>");
            put("\r\n*.*private static final long serialVersionUID.*\r\n*", "");
            put("\r\n*.*public static final long serialVersionUID.*\r\n*", "");
        }
    };
    private static final HashMap<String, String> J2DONETREPLACEfIRSTMAP = new HashMap<String, String>() {
        {
            put("(import.*[\r\n]*)+", "namespace " + NameSpace + "\r\n{\r\n");
            put("([\\w\\W]*)}", "$1\r\n\t}\r\n}");
        }
    };
    private static final HashMap<String, String> ADDUSERREPALCEALLMAP = new HashMap<String, String>() {
        {
            put("(public.*\\(.+(?<!p_User))\\)(\\s*[^;])", "$1, string p_User\\)$2");
            put("(public.*\\((?<!p_User))\\)(\\s*[^;])", "$1string p_User\\)$2");
            put("(return WebRequest.*\\(.*(?<!p_User))(\\);)", "$1, p_User$2");
        }
    };
    private static final HashMap<String, String> IADDUSERREPALCEALLMAP = new HashMap<String, String>() {
        {
            put("(\\(.+(?<!p_User))\\);", "$1,string p_User\\);");
            put("(\\((?<!p_User))\\);", "$1string p_User\\);");
        }
    };

    public static void setOutputLocation() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.showDialog(null, "确认输出目录");
        outputLocation = fileChooser.getSelectedFile().getAbsolutePath() + "\\";
        System.out.println(outputLocation);
    }

    public static boolean StringIsEmpty(String param) {
        return (null == param || param.equals(""));
    }

    public static List<File> getAllSubFiles(File[] files, String FileNameExtends) {
        if (StringIsEmpty(FileNameExtends)) {
            FileNameExtends = ".*";
        }
        List<File> resultFiles = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                resultFiles.addAll(getAllSubFiles(file.listFiles(), FileNameExtends));
            } else {
                if (file.getName().contains(FileNameExtends)) {
                    resultFiles.add(file);
                }
            }
        }
        return resultFiles;
    }

    public static List<File> getAllFiles(String FileNameExtends, boolean getAllSubFilesFlag, String nameSpace) {
        if (StringIsEmpty(nameSpace)) {
            nameSpace = NameSpace;
        } else {
            NameSpace = nameSpace;
        }
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setMultiSelectionEnabled(true);
        // chooser.showDialog(new JLabel(), "aaa");
        chooser.showOpenDialog(null);
        File[] files = chooser.getSelectedFiles();
        System.out.println();
        List<File> fileList = new ArrayList<>();
        if (getAllSubFilesFlag) {
            fileList = getAllSubFiles(files, FileNameExtends);
        } else {
            for (File file : files) {
                fileList.add(file);
            }
        }
        return fileList;
    }

    public static void convertJ2DoNetClass(String FileNameExtends, boolean getAllSubFilesFlag, String nameSpace) throws Exception {
        convertFiles(FileNameExtends, getAllSubFilesFlag, nameSpace, J2DONETREPLACEAllMAP, J2DONETREPLACEfIRSTMAP);
    }

    public static void addUser(String FileNameExtends, boolean getAllSubFilesFlag, String nameSpace) throws Exception {
        convertFiles(FileNameExtends, getAllSubFilesFlag, nameSpace, ADDUSERREPALCEALLMAP, null);
    }

    public static void IaddUser(String FileNameExtends, boolean getAllSubFilesFlag, String nameSpace) throws Exception {
        convertFiles(FileNameExtends, getAllSubFilesFlag, nameSpace, IADDUSERREPALCEALLMAP, null);
    }

    public static void convertFiles(String FileNameExtends, boolean getAllSubFilesFlag, String nameSpace, Map<String, String> replaceAllPattern,
            Map<String, String> replaceFirstPattern) throws Exception {
        // Runtime.getRuntime().exec("explorer.exe /select");
        List<File> fileList = getAllFiles(FileNameExtends, getAllSubFilesFlag, nameSpace);
        String totalString = "";
        String result = null;
        for (File file : fileList) {
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
            // result = totalString.replaceAll("", "");
            // result = result.replaceAll("", "");
            // result = result.replaceAll("", "");
            // result = result.replaceAll("", "");
            // result = result.replaceFirst("(import.*[\r\n]*)+", "namespace " + nameSpace + "\r\n{\r\n");
            // result = result.replaceAll("", "");
            // result = result.replaceFirst("([\\w\\W]*)}", "$1\r\n\t}\r\n}");
            // result = result.replaceAll("", "");
            // result = result.replaceAll(" Date ", " long? ");
            // result = result.replaceAll(" String ", " string ");
            // result = result.replaceAll(" extends ", " : ");
            // result = result.replaceAll(" Boolean ", " bool? ");
            // result = result.replaceAll(" Double ", " double? ");
            // result = result.replaceAll(" Long ", " long? ");
            result = ReplaceAllByPattern(totalString, replaceAllPattern, replaceFirstPattern);
            System.out.println(result);
            String fileName = file.getName().replace(".java", ".cs");
            File m_File = new File(outputLocation + fileName);
            FileOutputStream fileOutputStream = new FileOutputStream(m_File, false);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, "GB2312");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(result);
            bufferedWriter.close();
            outputStreamWriter.close();
            fileOutputStream.close();
            totalString = "";
            result = null;
        }
    }

    public static String ReplaceAllByPattern(String source, Map<String, String> patternReplaceAll, Map<String, String> patternReplaceFirst) {
        if (patternReplaceAll != null) {
            for (Map.Entry<String, String> map : patternReplaceAll.entrySet()) {
                Pattern pattern = Pattern.compile(map.getKey());
                source = pattern.matcher(source).replaceAll(map.getValue());
            }
        }
        if (patternReplaceFirst != null) {
            for (Map.Entry<String, String> map : patternReplaceFirst.entrySet()) {
                source = source.replaceFirst(map.getKey(), map.getValue());
            }
        }
        return source;
    }
}
