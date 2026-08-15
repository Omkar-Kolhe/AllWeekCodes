# 1_gemini_sol2.py
import sys


def main():
    tokens = sys.stdin.read().split()
    if not tokens:
        return

    iterator = iter(tokens)
    num_test_cases = int(next(iterator))

    for _ in range(num_test_cases):
        num_avengers = int(next(iterator))
        initial_resistance = int(next(iterator))

        cap_strength = int(next(iterator))
        cap_worthiness = int(next(iterator))

        max_allowed = cap_strength + cap_worthiness - initial_resistance

        total_convincing_time = 0
        avenger_data = []

        for _ in range(num_avengers):
            s_i = int(next(iterator))
            w_i = int(next(iterator))
            c_i = int(next(iterator))

            imp = s_i - w_i
            if imp < 0:
                imp = 0

            avenger_data.append((imp, c_i))
            total_convincing_time += c_i

        if max_allowed < 0:
            print("-1")
            continue

        dp_states = {0: 0}

        for weight, value in avenger_data:
            next_states = dp_states.copy()
            for current_weight, current_value in dp_states.items():
                next_weight = current_weight + weight
                next_value = current_value + value

                if next_weight <= max_allowed:
                    if next_weight not in next_states or next_value > next_states[next_weight]:
                        next_states[next_weight] = next_value
            dp_states = next_states

        max_saved = max(dp_states.values())
        print(total_convincing_time - max_saved)


if __name__ == "__main__":
    main()
