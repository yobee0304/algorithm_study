# 보석 쇼핑

# 1. 문제
https://programmers.co.kr/learn/courses/30/lessons/67258

# 2. 유형
* Two-pointers algorithm

# 3. 풀이
* 완전 탐색으로 문제를 풀 경우, O(n^2) 시간이 걸린다. (배열의 크기가 최대 100,000이므로 매우 느리다)
* 탐색을 O(n) 시간에 끝내기 위해 start, end 두 지점을 활용한 '투포인터 알고리즘'을 활용했다.
* start와 end 지점 사이에 있는 보석의 가짓수를 저장해두기 위해 dictionary 데이터 구조를 사용했다.

### 알고리즘 구현
1. start와 end 사이에 모든 보석이 존재하는 경우
* 현재 answer에 저장해놓은 범위와 길이를 비교하여, answer보다 길이가 작을 때만 answer를 갱신한다.
  * answer와 길이가 같을 때는, start의 숫자가 더 커지게 되므로 갱신할 필요가 없다.
* answer 갱신이 완료되면, start를 줄여 더 좁은 범위에서 조건을 만족하는지 확인한다.
```python
if len(jewlry) == jewlry_length:
    # 현재 범위 길이와 answer 길이 비교
    if answer_length > end - start + 1:
        answer_length = end - start + 1
        # 반복문을 돌 때, 이미 end에 1이 더해진 상황이므로 start에만 1을 더한다
        answer = [start+1, end]
    jewlry[gems[start]] -= 1
    # 존재하지 않는 보석은 딕테이션에서 삭제
    if jewlry[gems[start]] == 0:
        del jewlry[gems[start]]
    start += 1
    continue
```
2. start와 end 사이에 보석의 가짓수가 부족한 경우
* end에 있는 보석을 딕테이션에 추가한다.
* end의 범위를 늘려 조건에 만족하는 범위를 찾는다.
```python
if len(jewlry) != jewlry_length:
    # 딕테이션에 없던 보석은 새로 추가
    if not gems[end] in jewlry:
        jewlry[gems[end]] = 0
    jewlry[gems[end]] += 1
    end += 1
```

# 4. 후기
보석의 가짓수를 세기 위해 딕테이션을 사용했으나, value값에 해당 보석의 개수가 아닌 해당 보석의 인덱스를 갱신하며 탐색했다.

이 때, 가장 큰 문제점은 딕테이션을 통해 범위 값을 구하기 위해 필요한 min 인덱스와 max 인덱스 또한 탐색으로 구해야 한다는 것이다.

이때 수행 시간은 O(nm)이 되며 (m=보석의 가짓수), m이 최악의 경우 n이 된다는 것으로 미루어 보았을 때 매우 느리다는 것을 알 수 있었다.

단순 1차원 배열을 어떠한 특정 조건에 따라 탐색을 할 때, 투포인터 알고리즘은 아주 유용하게 쓸 수 있는 알고리즘인 것 같다.
