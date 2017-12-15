import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DFA {
    private static Map<String, Object> sensitiveMap;
    static {
        sensitiveMap = new HashMap<>();
        File file = new File("E:\\Project\\DFA\\data.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                addWord(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addWord(String line) {
        Map<String, Object> nowMap = sensitiveMap;
        for(int i = 0; i < line.length(); i ++) {
            String c = Character.toString(line.charAt(i));
            if(nowMap.containsKey(c)) {
                nowMap = (HashMap<String, Object>)nowMap.get(c);
            }
            else {
                Map<String, Object> child= new HashMap<>();
                child.put("end", 0);
                nowMap.put(c, child);
                nowMap = (HashMap)nowMap.get(c);
            }
        }
        nowMap.put("end", 1);
    }

    public static Set<String> test(String line) {
        Set<String> words= new HashSet<>();
        for (int i = 0; i < line.length(); i ++) {
            String key = Character.toString(line.charAt(i));
            if (sensitiveMap.containsKey(key)) {
                StringBuffer sb = new StringBuffer();
                Map<String, Object> nowMap = sensitiveMap;
                for (int j = i ; j < line.length(); j++) {
                    String c = Character.toString(line.charAt(j));
                    if(nowMap.containsKey(c)) {
                        sb.append(c);
                        nowMap = (HashMap<String, Object>) nowMap.get(c);
                        if (Integer.parseInt(nowMap.get("end").toString()) == 1) {
                            words.add(sb.toString());
                        }
                    }
                    else break;
                }
            }
        }

        return words;
    }

    public static void main(String[] args) {
        addWord("知道");
        Set<String> strs = test("你是法轮功是？天安门事件你知道吗？淡蓝色的京东方水电费是否时间到了福建省了的咖啡机适得府君书两地分居欧舒丹浇浇水砥砺奋进觉得快见风使舵会计师就是健康精灵就类似觉得快接口旧世纪东方加链接了搜饥饿贾凯里尼健身房健身房加上了的芳姐了开始酒店方就是sb屌");
        for(String str: strs) System.out.println(str);
    }
}
