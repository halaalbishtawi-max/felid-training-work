package university2

class Course {

    String title
    String code
    Integer creditHours

    static hasMany = [enrollments: Enrollment]

    static constraints = {

        title blank: false

        code blank: false,
                unique: true

        creditHours range: 1..6
    }
}