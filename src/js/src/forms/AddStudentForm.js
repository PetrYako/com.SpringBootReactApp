import React from 'react';
import { Formik } from 'formik';
import { Input, Tag, Button } from 'antd';
import { addNewStudent } from '../client';

const inputBottomMargin = {marginBottom: '10px'};

const AddStudentForm = (props) => (
    <div>
    <Formik
      initialValues={{firstName: '', lastName: '', email: '', gender: ''}}
      validate={values => {
        const errors = {};
        if (!values.firstName) {
            errors.firstName = 'First name required';
        }

        if (!values.lastName) {
            errors.lastName = 'Last name required';
        }

        if (!values.email) {
          errors.email = 'Email required';
        } else if (
          !/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,}$/i.test(values.email)
        ) {
          errors.email = 'Invalid email address';
        }

        if (!values.gender) {
            errors.gender = 'Gender required';
        } else if (!['MALE', 'male', 'FEMALE', 'female'].includes(values.gender)) {
            errors.gender = 'Invalid gender (MALE, male, FEMALE, female)';
        }
        return errors;
      }}
      onSubmit={(student, { setSubmitting }) => {
         addNewStudent(student).then(() => {
             props.onSuccess();
         })
         .finally(() => {
             setSubmitting(false);
         });
      }}
    >
      {({
        values,
        errors,
        touched,
        handleChange,
        handleBlur,
        handleSubmit,
        isSubmitting,
        submitForm,
        isValid
        /* and other goodies */
      }) => (
        <form onSubmit={handleSubmit}>
         {errors.firstName && touched.firstName && <Tag>{errors.firstName}</Tag>}
          <Input
            style={inputBottomMargin}
            type="firstName"
            name="firstName"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.firstName}
            placeholder="First name"
          />
          {errors.lastName && touched.lastName && <Tag>{errors.lastName}</Tag>}
          <Input
            style={inputBottomMargin}
            type="lastName"
            name="lastName"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.lastName}
            placeholder="Last name"
          />
          {errors.email && touched.email && <Tag>{errors.email}</Tag>}
          <Input
            style={inputBottomMargin}
            type="email"
            name="email"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.email}
            placeholder="Email"
          />
          {errors.gender && touched.gender && <Tag>{errors.gender}</Tag>}
          <Input
            style={inputBottomMargin}
            type="gender"
            name="gender"
            onChange={handleChange}
            onBlur={handleBlur}
            value={values.gender}
            placeholder="Gender"
          />
          <Button
           onClick={() => submitForm()}
           type="submit"
           disabled={isSubmitting | (touched && !isValid)}>
            Submit
          </Button>
        </form>
      )}
    </Formik>
  </div>
);

export default AddStudentForm;