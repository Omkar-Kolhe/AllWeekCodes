import sys

def process_mountain_signals():
    input_data = sys.stdin.read().split()
    if not input_data:
        return
    
    total_test_cases = int(input_data[0])
    current_input_index = 1
    final_output = []
    
    for _ in range(total_test_cases):
        num_towers = int(input_data[current_input_index])
        current_input_index += 1
        
        signal_strengths = [int(strength) for strength in input_data[current_input_index : current_input_index + num_towers]]
        current_input_index += num_towers
        
        visible_counts = [0] * num_towers
        stronger_tower_indices = []
        
        for current_tower in range(num_towers - 1, -1, -1):
            while stronger_tower_indices and signal_strengths[stronger_tower_indices[-1]] <= signal_strengths[current_tower]:
                stronger_tower_indices.pop()
            
            if not stronger_tower_indices:
                visible_counts[current_tower] = num_towers - 1 - current_tower
            else:
                visible_counts[current_tower] = stronger_tower_indices[-1] - current_tower
                
            stronger_tower_indices.append(current_tower)
            
        final_output.append(" ".join(map(str, visible_counts)))
        
    print("\n".join(final_output))

if __name__ == '__main__':
    process_mountain_signals()