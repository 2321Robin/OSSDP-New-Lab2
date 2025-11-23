/**
 * 测试用例设计原则：结合等价类划分、边界值分析与典型场景覆盖，确保对普通情况、全不相交情况以及多重子集判定进行验证。
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class L2023112332_6_Test {
    private final Solution6 solution = new Solution6();

    /**
     * 测试目的：验证存在多个嵌套子集时仅保留非子集清单。
     * 用例：favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]，期望 [0,1,4]。
     */
    @Test
    public void testMultipleSubsetsScenario() {
        String[][] raw = {
                {"leetcode", "google", "facebook"},
                {"google", "microsoft"},
                {"google", "facebook"},
                {"google"},
                {"amazon"}
        };
        List<Integer> result = solution.peopleIndexes(buildFavoriteCompanies(raw));
        Assert.assertEquals(Arrays.asList(0, 1, 4), result);
    }

    /**
     * 测试目的：验证当一个列表完全被另一个覆盖时能正确剔除子集。
     * 用例：favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]，期望 [0,1]。
     */
    @Test
    public void testDirectSubsetElimination() {
        String[][] raw = {
                {"leetcode", "google", "facebook"},
                {"leetcode", "amazon"},
                {"facebook", "google"}
        };
        List<Integer> result = solution.peopleIndexes(buildFavoriteCompanies(raw));
        Assert.assertEquals(Arrays.asList(0, 1), result);
    }

    /**
     * 测试目的：验证所有收藏清单互不为子集时返回所有下标。
     * 用例：favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]，期望 [0,1,2,3]。
     */
    @Test
    public void testAllIndependentLists() {
        String[][] raw = {
                {"leetcode"},
                {"google"},
                {"facebook"},
                {"amazon"}
        };
        List<Integer> result = solution.peopleIndexes(buildFavoriteCompanies(raw));
        Assert.assertEquals(Arrays.asList(0, 1, 2, 3), result);
    }

    /**
     * 测试目的：验证不同长度的列表中只有一个独特大集合被保留。
     * 用例：favoriteCompanies = [["a","b","c","d"],["a","b","c"],["b","c"],["c","d"],["d"]]，期望 [0,3]。
     */
    @Test
    public void testMixedLengthsWithOverlap() {
        String[][] raw = {
                {"a", "b", "c", "d"},
                {"a", "b", "c"},
                {"b", "c"},
                {"c", "d"},
                {"d"}
        };
        List<Integer> result = solution.peopleIndexes(buildFavoriteCompanies(raw));
        Assert.assertEquals(Arrays.asList(0, 3), result);
    }

    private List<List<String>> buildFavoriteCompanies(String[][] raw) {
        List<List<String>> list = new ArrayList<List<String>>();
        for (String[] row : raw) {
            list.add(Arrays.asList(row));
        }
        return list;
    }
}