
def solution(n, chem):
    # 냉장고의 개수
    ans = 1

    # 최저 온도를 기준으로 정렬
    chem = sorted(chem, key=lambda x: (x[0], x[1]))

    maxTemper = chem[0][1]

    for i in range(1, len(chem)):
        low, high = chem[i]
        # 다음 화학물질의 최저 온도가 범위를 벗어나면
        if low > maxTemper:
            # 냉장고 개수 + 1
            ans += 1
            maxTemper = high
        # 만약에 포함되면 범위를 좁힘
        else:
            maxTemper = min(maxTemper, high)

    return ans


if __name__ == "__main__":
    # input
    n = int(input())
    chem = []
    for i in range(n):
        l, h = input().split(" ")
        chem.append([int(l), int(h)])
    print(solution(n, chem))