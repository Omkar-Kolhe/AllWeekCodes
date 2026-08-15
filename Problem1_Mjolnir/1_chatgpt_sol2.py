import sys


def solve():
    data = list(map(int, sys.stdin.buffer.read().split()))
    pos = 0

    cases = data[pos]
    pos += 1

    answers = []

    for _ in range(cases):
        n = data[pos]
        r = data[pos + 1]
        pos += 2

        cap = data[pos] + data[pos + 1]
        pos += 2

        items = []
        resistance = r

        for _ in range(n):
            s, w, c = data[pos], data[pos + 1], data[pos + 2]
            pos += 3

            damage = max(0, s - w)
            resistance += damage

            if damage:
                items.append((damage, c))

        if resistance <= cap:
            answers.append("0")
            continue

        need = resistance - cap
        INF = 10**18
        dp = {0: 0}

        for damage, cost in items:
            updates = {}

            for saved, spent in dp.items():
                new_saved = min(need, saved + damage)
                new_cost = spent + cost

                if new_saved not in dp or new_cost < dp[new_saved]:
                    if new_saved not in updates or new_cost < updates[new_saved]:
                        updates[new_saved] = new_cost

            dp.update(updates)

        answers.append(str(dp.get(need, -1)))

    sys.stdout.write("\n".join(answers))


if __name__ == "__main__":
    solve()
