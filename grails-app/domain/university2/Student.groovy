package university2

class Student {

    String name
    String email
    String studentNumber

    static constraints = {
        name blank: false
        email unique: true, email: true
        studentNumber blank: false
    }
}