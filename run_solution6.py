#!/usr/bin/env python3
from typing import List, Set


def people_indexes(favorite_companies: List[List[str]]) -> List[int]:
    n = len(favorite_companies)
    favorite_sets: List[Set[str]] = [set(lst) for lst in favorite_companies]
    ans: List[int] = []
    for i in range(n):
        is_subset = False
        current = favorite_sets[i]
        for j in range(n):
            if i == j:
                continue
            if len(favorite_companies[j]) < len(favorite_companies[i]):
                continue
            if favorite_sets[j].issuperset(current):
                is_subset = True
                break
        if not is_subset:
            ans.append(i)
    return ans


def run_and_check(raw, expected, name):
    result = people_indexes([list(r) for r in raw])
    if result == expected:
        print(f"{name}: PASS")
        return True
    else:
        print(f"{name}: FAIL")
        print("  Expected:", expected)
        print("  Got     :", result)
        return False


def main():
    failed = 0

    raw1 = [
        ["leetcode", "google", "facebook"],
        ["google", "microsoft"],
        ["google", "facebook"],
        ["google"],
        ["amazon"],
    ]
    if not run_and_check(raw1, [0, 1, 4], "testMultipleSubsetsScenario"):
        failed += 1

    raw2 = [
        ["leetcode", "google", "facebook"],
        ["leetcode", "amazon"],
        ["facebook", "google"],
    ]
    if not run_and_check(raw2, [0, 1], "testDirectSubsetElimination"):
        failed += 1

    raw3 = [["leetcode"], ["google"], ["facebook"], ["amazon"]]
    if not run_and_check(raw3, [0, 1, 2, 3], "testAllIndependentLists"):
        failed += 1

    raw4 = [["a", "b", "c", "d"], ["a", "b", "c"], ["b", "c"], ["c", "d", "e"], ["d"]]
    if not run_and_check(raw4, [0, 3], "testMixedLengthsWithOverlap"):
        failed += 1

    if failed == 0:
        print("ALL TESTS PASSED")
        return 0
    else:
        print(f"{failed} test(s) FAILED")
        return 2


if __name__ == "__main__":
    import sys

    sys.exit(main())
