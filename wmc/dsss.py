print("dsss program")

#data = input("Enter the user data")
data = "11"
barker_code = "10110111000"
#transmitter side
spread_signal = ""
for i in range(0,len(data)):
    bit = data[i]
    for j in range(0,len(barker_code)):
        xor_result = int(bit) ^ int(barker_code[j])
        spread_signal += str(xor_result)
        

print(spread_signal)

#receiver side

receiver = "0100100011101001001111"

decision = ""
for i in range(0,len(data)):
    start = i * len(barker_code)
    end = start + len(barker_code)
    received_block = receiver[start:end]
    print(received_block)
    count = 0
    
    for j in range(0,len(barker_code)):
        xor = int(received_block[j]) ^ int(barker_code[j])
        if xor == 1:
            count = count + 1

    if(count < len(barker_code)/2):
        decision = "0"
    else:
        decision = "1"

    print(decision)
