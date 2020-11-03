def solution(gems):
    answer = []
    answer_length = 100001
    start, end = 0, 0
    jewlry_length = len(set(gems))
    jewlry = dict()

    while True:
        if start >= len(gems):
            break

        # jewlry에 모든 보석이 존재할 때
        if len(jewlry) == jewlry_length:
            # 길이가 더 작을 때만 갱신
            if answer_length > end - start + 1:
                answer_length = end - start + 1
                answer = [start+1, end]
            jewlry[gems[start]] -= 1
            # 없는 보석은 삭제
            if jewlry[gems[start]] == 0:
                del jewlry[gems[start]]
            start += 1
            continue

        if end >= len(gems):
            break

        # jewlry에 보석이 부족한 경우
        if len(jewlry) != jewlry_length:
            if not gems[end] in jewlry:
                jewlry[gems[end]] = 0
            jewlry[gems[end]] += 1
            end += 1

    return answer

if __name__ == "__main__":
    g = ["DIA", "EM", "EM", "RUB", "DIA"]
    print(solution(g))