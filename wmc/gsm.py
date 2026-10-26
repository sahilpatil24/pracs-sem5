import hashlib as h

message = "hello"
Ki = "1234567890ABCDEF"
Rand = "ABCDEF1234567890"

def A3(Ki, Rand):
    data = Ki + Rand
    hash_value = h.sha256(data.encode()).hexdigest()
    return hash_value[:8]

def A8(Ki, Rand):
    data = Ki + Rand
    hash_value = h.sha256(data.encode()).hexdigest()
    return hash_value[8:24]

def A5(message, Kc):
    encrypted = ""
    for i in range(len(message)):
        encrypted += chr(ord(message[i]) ^ ord(Kc[i % len(Kc)]))
    return encrypted

print("Ki: ", Ki)
print("Rand: ", Rand)
print(A3(Ki, Rand))
Kc = A8(Ki, Rand)
print("Kc: " + Kc)
print("encrypted message: " + A5(message, Kc))
