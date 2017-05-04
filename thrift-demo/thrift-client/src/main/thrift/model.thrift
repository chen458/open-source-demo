include "fb303.thrift"
include "types.thrift"

namespace java com.chen.demo.thrift.model

struct Person {
    1:string name,
    2:i16 age,
    3:bool sex,
    4:double salary
}

struct QueryParameter{
    1:i16 ageStart,
    2:i16 ageEnd
}

