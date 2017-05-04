include "model.thrift"

namespace java com.chen.demo.thrift

service IHelloWorldService{
    string sayHello(1:string name);

    list<model.Person> getPersonList(1:model.QueryParameter parmeter);
}