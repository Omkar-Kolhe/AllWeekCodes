#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

struct Submission {
    long long processingTime;
    long long deadline;
};

bool compareSubmissions(const Submission& a, const Submission& b) {
    if (a.deadline == b.deadline) {
        return a.processingTime > b.processingTime;
    }
    return a.deadline < b.deadline;
}

bool isFeasible(int containerCount, const std::vector<Submission>& submissions) {
    std::priority_queue<long long, std::vector<long long>, std::greater<long long>> containerAvailableTimes;
    
    for (int i = 0; i < containerCount; ++i) {
        containerAvailableTimes.push(0);
    }
    
    for (const auto& submission : submissions) {
        long long earliestAvailableTime = containerAvailableTimes.top();
        containerAvailableTimes.pop();
        
        long long completionTime = earliestAvailableTime + submission.processingTime;
        
        if (completionTime > submission.deadline) {
            return false;
        }
        
        containerAvailableTimes.push(completionTime);
    }
    
    return true;
}

void processTestCase() {
    int numberOfSubmissions;
    std::cin >> numberOfSubmissions;
    
    std::vector<Submission> submissions(numberOfSubmissions);
    for (int i = 0; i < numberOfSubmissions; ++i) {
        std::cin >> submissions[i].processingTime >> submissions[i].deadline;
    }
    
    std::sort(submissions.begin(), submissions.end(), compareSubmissions);
    
    int low = 1;
    int high = numberOfSubmissions;
    int minimumContainersRequired = numberOfSubmissions;
    
    while (low <= high) {
        int mid = low + (high - low) / 2;
        if (isFeasible(mid, submissions)) {
            minimumContainersRequired = mid;
            high = mid - 1;
        } else {
            low = mid + 1;
        }
    }
    
    std::cout << minimumContainersRequired << "\n";
}

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(NULL);
    
    int testCases;
    if (std::cin >> testCases) {
        while (testCases--) {
            processTestCase();
        }
    }
    
    return 0;
}
