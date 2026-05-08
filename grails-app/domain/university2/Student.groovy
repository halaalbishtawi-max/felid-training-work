class Student {

    String name
    String email
    String studentNumber

    static hasMany = [enrollments: Enrollment]

    static constraints = {
        name blank: false
        email blank: false, unique: true, email: true
        studentNumber blank: false
    }
}