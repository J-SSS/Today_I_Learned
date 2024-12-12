import java.util.*;
import java.util.concurrent.atomic.*;
class Solution {
    public String[] solution(String[] names) {
        String[] answer = {};
        AtomicInteger idx = new AtomicInteger(0);
        answer = Arrays.stream(names).filter(e -> {
           return idx.getAndIncrement() % 5 == 0;
        }).toArray(String[]::new);

        return answer;
    }
}