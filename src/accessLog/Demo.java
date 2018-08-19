package accessLog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Demo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("")));
        //统计发生请求次数
        Long count = 0L;
        //POST请求的次数
        Long postCount = 0L;
        //GET提请求次数
        Long getCount = 0L;
        //统计URL和请求次数
        HashMap<String, Long> maps = new HashMap<String, Long>();
        while (br.ready()) {
            String str = br.readLine().trim();
            //传输为空
            if (str == null || str.equals("")) {
                continue;
            }
            String[] strArrays = str.split(" ");
            String type = strArrays[0];
            String url = strArrays[1];
            //传输类型只有两种
            if ("POST".equals(type)) {
                postCount = postCount + 1;
            } else if ("GET".equals(type)) {
                getCount = getCount + 1;
            } else {
                continue;
            }
            //统计存储
            if (maps.containsKey(url)) {
                maps.put(url, maps.get(url) + 1);
            } else {
                maps.put(url, 1L);
            }
        }
        //总数
        count = postCount + getCount;
        //排名前十
        String[] urlArray = new String[10];
        Long[] urlCountArray = new Long[10];
        //部分排序，堆排序不是很会
        if (count >= 10) {
            //初始化
            int index = 0;
            Set<Map.Entry<String, Long>> entries = maps.entrySet();
            for (Map.Entry<String, Long> entry : entries) {
                String key = entry.getKey();
                Long value = entry.getValue();
                if (index == 0) {
                    urlArray[0] = key;
                    urlCountArray[0] = value;
                } else {
                    int pre = 0;
                    if (index < 10) {
                        pre = index - 1;
                    } else {
                        pre = 9;
                    }
                    while (pre >= 0 && urlCountArray[pre] < value) {
                        pre--;
                    }
                }
            }
        }
    }
}