package university2

class DashboardController {

    def index() {
        [
            studentCount: Student.count(),
            courseCount: Course.count(),
            enrollmentCount: Enrollment.count()
        ]
    }
}