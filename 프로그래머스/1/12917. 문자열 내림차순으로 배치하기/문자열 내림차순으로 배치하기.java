import java.util.*;
import java.util.stream.*;
class Solution {
    public String solution(String s) {
        return Stream.of(s.split("")).sorted((a, b) -> b.charAt(0) - a.charAt(0)).collect(Collectors.joining());
    }
}