import React, { Component } from 'react';
import Container from './Conatiner';
import AddStudentFrom from './forms/AddStudentForm';
import Footer from './Footer';
import 'antd/dist/antd.css';
import './App.css';
import { getAllStudents, deleteStudent } from './client';
import { Table, Avatar, Spin, Empty, Modal, Button } from 'antd';
import { errorNotification, successNotification } from './Notification';

class App extends Component {
  state = {
    students: [],
    isFetching: false,
    isAddStudentFromIsVisible: false
  }

  componentDidMount() {
    this.fetchStudents();
  }

  openAddStudentModal = () => this.setState({ isAddStudentFromIsVisible: true });
  closeAddStudentModal = () => this.setState({ isAddStudentFromIsVisible: false });

  fetchStudents = () => {
    this.setState({
      isFetching: true
    })
    getAllStudents()
    .then(res => res.json()
    .then(students => {
      console.log(students);
      this.setState({
        students,
        isFetching: false
      });
    }))
    .catch(error => {
      console.log(error.error);
      const message = error.error.message;
      const description = error.error.error;
      errorNotification(message, description);
      this.setState({
        isFetching: false
      })
    });
  }

  deleteStudent = (studentId) => {
    deleteStudent(studentId).then(() => {
        successNotification("Student deleted", "${studentId} was deleted");
        this.fetchStudents();
    }).catch(error => {
      errorNotification(`${error.error.message}`, `${error.error.error}`);
    });
  }


  render() {
      const { students, isFetching, isAddStudentFromIsVisible } = this.state;

      const commonElements = () => (
        <div>
          <Modal
          title='Add new student'
          visible={isAddStudentFromIsVisible}
          onOk={this.closeAddStudentModal}
          onCancel={this.closeAddStudentModal}
          width={1000}>
            <AddStudentFrom
            onSuccess={() => {
              this.closeAddStudentModal();
              this.fetchStudents();
            }} 
            />
          </Modal>
          <Footer
           numberOfStudents={students.length}
           handleAddStudentClickEvent={this.openAddStudentModal}/>
        </div>
      )

      if (isFetching) {
        return (
          <Container>
            <Spin size='large' style={{marginTop: '50px'}}/>
          </Container>
        );
      }

      if (students && students.length) {
        const columns = [
          {
            title: '',
            key: 'avatar',
            render: (text, student) => (
              <Avatar size='large'>
                {`${student.firstName.charAt(0).toUpperCase()}${student.lastName.charAt(0).toUpperCase()}`}
              </Avatar>
            )
          },
          {
            title: 'Student Id',
            dataIndex: 'studentId',
            key: 'studentId'
          },
          {
            title: 'First Name',
            dataIndex: 'firstName',
            key: 'firstName'
          },
          {
            title: 'Last Name',
            dataIndex: 'lastName',
            key: 'lastName'
          },
          {
            title: 'Email',
            dataIndex: 'email',
            key: 'email'
          },
          {
            title: 'Gender',
            dataIndex: 'gender',
            key: 'gender'
          },
          {
            title: '',
            dataIndex: '',
            key: 'delete',
            render: (text, record) => (
              <a onClick={() => this.deleteStudent(record.studentId)}>Delete</a>
            )
          },
          {
            title: '',
            dataIndex: '',
            key: 'edit',
            render: () => <a>Edit</a>
          }
        ];

        return (
          <Container>
          <Table
          columns={columns}
          dataSource={students}
          pagination={false}
          rowKey='studentId'/>
          {commonElements()}
          </Container>
      );
    }

   return (
      <Container>
        <Empty description={
          <h1>No students found</h1>
        }/>
        {commonElements()}
      </Container>
   );
  }
}

export default App;
