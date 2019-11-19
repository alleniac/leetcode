class Solution:
    def splitArray(self, nums: List[int], m: int) -> int:
        def count(nums, m, mid):
            cnt = 1
            total = 0
            for num in nums:
                total += num
                if total > mid:
                    total = num
                    cnt += 1
            return cnt
        total = sum(nums)
        largest = max(nums)
        if m == 1:
            return total
        start = largest
        end = total
        pos = -1
        while start <= end:
            mid = math.floor((start + end) / 2)
            fMid = count(nums, m, mid)
            if fMid <= m:
                pos = mid
                end = mid - 1
            else:
                start = mid + 1
        return pos
        
        