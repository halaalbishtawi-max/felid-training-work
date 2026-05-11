package university2

import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class StudentController {

    StudentService studentService
    EnrollmentService enrollmentService

    static allowedMethods = [
            save: "POST",
            update: "PUT",
            delete: "DELETE"
    ]

    def index(Integer max) {

        params.max = Math.min(max ?: 10, 100)

        respond studentService.list(params),
                model: [studentCount: studentService.count()]
    }

    def search(String name) {

        def students = Student.findAllByNameIlike("%${name}%")

        render(
                view: "index",
                model: [
                        studentList : students,
                        studentCount: students.size()
                ]
        )
    }

    def show(Long id) {

        respond studentService.get(id)
    }

    def create() {

        respond new Student(params)
    }

   def save(Student student) {

    if (student == null) {
        notFound()
        return
    }

    if (student.hasErrors()) {
        respond student.errors, view:'create'
        return
    }

    student.save flush:true

    flash.message = "Student created successfully!"

    redirect student
}
        try {

            studentService.save(student)

        } catch (ValidationException e) {

            respond student.errors, view: 'create'
            return
        }

        request.withFormat {

            form multipartForm {

                flash.message = message(
                        code: 'default.created.message',
                        args: [
                                message(code: 'student.label', default: 'Student'),
                                student.id
                        ]
                )

                redirect student
            }

            '*' {
                respond student, [status: CREATED]
            }
        }
    }

    def edit(Long id) {

        respond studentService.get(id)
    }

    def update(Student student) {

        if (student == null) {
            notFound()
            return
        }

        try {

            studentService.save(student)

        } catch (ValidationException e) {

            respond student.errors, view: 'edit'
            return
        }

        request.withFormat {

            form multipartForm {

                flash.message = message(
                        code: 'default.updated.message',
                        args: [
                                message(code: 'student.label', default: 'Student'),
                                student.id
                        ]
                )

                redirect student
            }

            '*' {
                respond student, [status: OK]
            }
        }
    }

    def delete(Long id) {

        if (id == null) {
            notFound()
            return
        }

        studentService.delete(id)

        request.withFormat {

            form multipartForm {

                flash.message = message(
                        code: 'default.deleted.message',
                        args: [
                                message(code: 'student.label', default: 'Student'),
                                id
                        ]
                )

                redirect action: "index", method: "GET"
            }

            '*' {
                render status: NO_CONTENT
            }
        }
    }

    def testGpa(Long id) {

        double gpa = enrollmentService.calculateGpa(id)

        render "Student GPA = ${gpa}"
    }

    protected void notFound() {

        request.withFormat {

            form multipartForm {

                flash.message = message(
                        code: 'default.not.found.message',
                        args: [
                                message(code: 'student.label', default: 'Student'),
                                params.id
                        ]
                )

                redirect action: "index", method: "GET"
            }

            '*' {
                render status: NOT_FOUND
            }
        }
    }
}