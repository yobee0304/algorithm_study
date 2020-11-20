# 무지의 먹방 라이브

## 1. 문제
https://programmers.co.kr/learn/courses/30/lessons/42891

## 2. 유형
* Priority Queue(우선순위 큐)

## 3. 풀이
* 먹는데 가장 적게 걸리는 음식부터 제거한다.
* 이때 가장 적게 걸리는 음식을 선택하기 위해 '우선순위 큐'를 사용한다.
```python
pq = PriorityQueue()
for i in range(len(food_times)):
    pq.put((food_times[i], i+1))
```
* 해당 음식을 모두 먹기 위해 걸리는 시간은 **([해당 음식의 시간] - [이전 음식의 시간]) * [남은 음식의 개수]** 가 된다.
  * [해당 음식의 시간] - [이전 음식의 시간]의 이유는, 이전 음식을 빼면서 이미 전체의 음식에 그만큼의 시간이 빠졌기 때문이다.
  * 처음 빠지는 음식의 경우 [이전 음식의 시간]은 0이다.
  * 해당 음식을 모두 먹었으면, 전체 길이를 1 줄이고 걸린 시간만큼 sum_num에 더한다.
* 이 과정을 sum_num이 k보다 커질때 까지 반복한다.
```python
while sum_num + (pq.queue[0][0] - previous_num) * length <= k:
    now = pq.get()[0]
    sum_num += (now - previous_num) * length
    length -= 1
    previous_num = now
```
* 남은 음식들을 기존의 인덱스를 기준으로 재정렬한다.
```python
result = sorted(pq.queue, key=lambda x:x[1])
```
* 결과값은 남은 시간 k만큼 이동한 위치의 음식이 된다.
```python
return result[(k - sum_num) % len(result)][1]
```

## 4. 후기
가장 적게 걸리는 음식부터 처리해야하는 아이디어는 떠올렸으나, 이 값을 전체 리스트에서 계속 빼면서 진행했기 때문에 시간적인 문제가 생겼다.

또한 모두 먹은 음식을 리스트에서 제거하지 않고 진행했기 때문에 문제가 생긴 것 같다.
