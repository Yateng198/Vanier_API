
Implement a vanierapi_lib to store all 3 entities, and also implemented the repositories, services and controllers inside this lib for all the 3 entities.
It was one lib for each entity at bigaining, but I added an field which is a list of course teaching in the teacher entity, and also a list of course enrolled in the student entity.
Which is causing the recursion relations between the 3 entities so that have to put all them into one lib.

In this project, acheived CRUD operations indipendently for each entity and also added 3 mehtods:
a, Register a student to a course by student id and course id.
b, Remove a student from a specific course by student id and course id.
c, Assign / Change teacher of a specific course by teacher id and course id.
Also, all the CRUD and the 3 methods operatons are synchronized with database.

For example when you adding a new student with a valide course enrolled list,(in this list, the course names are stored as a string, !IMPORTANT!: format is("Course Name: courseName")), system will take the courseName to check if we have this course in database, if yes, this student will add into the students list of this course object. if no, error msg will shown and the student won't be added into database.
For delete student, will automatically delete this student from all the course he/she is currently enrolled.
For adding new course, you will need indicate a existed teacher for this new course, at least indicate the teacher id, otherwise error msg will shown. while you entered the right teacher, this new courseName will be added into teacher's teaching course list(!IMPORTANT!: format is("Course Name: courseName")). Also if you indicate the students list for this new course, this course name will be added into the student's enrolled list as well in the same format. But if you forgot the student id, error msg will shown.

For both 3 updates method, basically the doing the same as adding, synchronized all the 3 entities if there is any in the new object.

For register a student to a course, if this student is already enrolled, error message will shown, else will adding this student into the students list of this course and also adding this course name into student enrolled list.(Same for remove but reverse).
For assign / change the teacher, this course will added into teaching list, if already teaching this course, error msg will shown. Also this course name will removed from previous teacher's teaching list.

In my demo, the course id 17 (Computer Security) is already full, 30 students, if you try to register anyone else, error msg will shown. You can also try to add student to another course to test this function. Else, if student already enrolled, error msg will shown as well.

!!!IMPORTANT!!!
When you need to indicate teh Teaching list or course enrolled list, make sure indicate the course name in this format: "Course Name: courseName".
GitHub: https://github.com/Yateng198/Vanier_API

