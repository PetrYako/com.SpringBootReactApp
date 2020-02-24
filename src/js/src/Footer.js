import React from 'react';
import Container from './Conatiner';
import { Button, Avatar } from 'antd';
import './Footer.css';

const Footer = (props) => (
    <div className='footer'>
        <Container>
            {props.numberOfStudents !== undefined ?
            <Avatar
            size='large'
            style={{marginRight:'5px', marginBottom:'5px', backgroundColor:'#85C1E9'}}>
            {props.numberOfStudents}    
            </Avatar> : null
            }
            <Button type='primary'
                    shape='circle'
                    icon='plus'
                    size='large'
                    onClick={() => props.handleAddStudentClickEvent()}/>
        </Container>
    </div>
);

export default Footer;