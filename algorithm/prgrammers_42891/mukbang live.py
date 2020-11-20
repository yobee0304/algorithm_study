from queue import PriorityQueue

def solution(food_times, k):
    # 모든 음식을 먹고 시간이 남는다면
    # -1을 리턴
    if sum(food_times) <= k:
        return -1

    length = len(food_times)
    # 우선순위 큐 선언
    pq = PriorityQueue()
    for i in range(length):
        pq.put((food_times[i], i+1))

    sum_num = 0
    previous_num = 0

    # k시간이 더 이상 남지 않을 때 까지 반복
    while sum_num + (pq.queue[0][0] - previous_num) * length <= k:
        now = pq.get()[0]
        sum_num += (now - previous_num) * length
        # 음식이 제거됬으므로 길이 1 감소
        length -= 1
        # 다음 음식의 총 시간을 구하기 위해 이전 음식의 시간 저장
        previous_num = now

    # 기존 인덱스를 기준으로 재정렬
    result = sorted(pq.queue, key=lambda x:x[1])

    # 남은 k 시간만큼 진행된 위치의 값을 반환
    return result[(k - sum_num) % len(result)][1]

if __name__ == "__main__":
    ft = [3, 1, 2]
    k = 5
    print(solution(ft, k))