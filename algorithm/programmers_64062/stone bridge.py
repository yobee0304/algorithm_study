# Binary Search
def solution(stones, k):
    # 건널 수 있는 친구들의 수 범위
    left, right = 1, 200000000

    while left < right:
        mid = (left + right) // 2
        stones_cpy = stones.copy()
        check = True
        cnt = 0
        # mid만큼 돌다리르 제거하여 친구들이 모두 건널 수 있는 지 확인
        for i in range(len(stones_cpy)):
            stones_cpy[i] -= mid
            if stones_cpy[i] <= 0:
                cnt += 1
            else:
                cnt = 0

            # 못 건너는 범위가 k보다 커지면 건널 수 없음(False)
            if cnt >= k:
                check = False
                break

        if check is True:
            left = mid + 1
        elif check is False:
            right = mid - 1

    return left

if __name__ == "__main__":
    s = [2, 4, 5, 3, 2, 1, 4, 2, 5, 1]
    k = 3
    print(solution(s, k))