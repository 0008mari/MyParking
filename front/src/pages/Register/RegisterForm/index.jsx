import React from "react";
import { useMutation } from "@tanstack/react-query";
import { Form, Input, Button } from "antd";
import { appClient } from "../../../api";
import { useNavigate } from "react-router-dom";

const formItemLayout = {
  labelCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 8,
    },
  },
  wrapperCol: {
    xs: {
      span: 24,
    },
    sm: {
      span: 16,
    },
  },
};
const tailFormItemLayout = {
  wrapperCol: {
    xs: {
      span: 24,
      offset: 0,
    },
    sm: {
      span: 16,
      offset: 8,
    },
  },
};

const RegistrationForm = () => {
  const [form] = Form.useForm();
  const navigate = useNavigate();

  const postRegister = async ({ username, email, password }) => {
    const data = await appClient.post("/register", {
      username,
      email,
      password,
    });
    return data;
  };
  const { mutate } = useMutation(postRegister, {
    onSuccess: (data) => {
      navigate("/login");
    },
    onError: (error) => {
      console.log(error);
    },
  });

  const onFinish = (values) => {
    mutate(values);
  };

  return (
    <Form
      {...formItemLayout}
      form={form}
      name="register"
      onFinish={onFinish}
      scrollToFirstError
    >
      <Form.Item
        name="email"
        label="이메일"
        rules={[
          {
            type: "email",
            message: "유효한 이메일을 입력해주세요",
          },
          {
            required: true,
            message: "이메일을 입력해주세요",
          },
        ]}
      >
        <Input />
      </Form.Item>

      <Form.Item
        name="password"
        label="패스워드"
        rules={[
          {
            required: true,
            message: "비밀번호를 입력해주세요",
          },
        ]}
        hasFeedback
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        name="confirm"
        label="패스워드 확인"
        dependencies={["password"]}
        hasFeedback
        rules={[
          {
            required: true,
            message: "비밀번호를 확인해주세요",
          },
          ({ getFieldValue }) => ({
            validator(_, value) {
              if (!value || getFieldValue("password") === value) {
                return Promise.resolve();
              }

              return Promise.reject(
                new Error("패스워드와 패스워드 학인은 동일해야합니다")
              );
            },
          }),
        ]}
      >
        <Input.Password />
      </Form.Item>

      <Form.Item
        name="username"
        label="닉네임"
        rules={[
          {
            required: true,
            message: "닉네임을 입력해주세요",
            whitespace: true,
          },
        ]}
      >
        <Input />
      </Form.Item>

      <Form.Item {...tailFormItemLayout}>
        <Button type="primary" htmlType="submit">
          Register
        </Button>
      </Form.Item>
    </Form>
  );
};

export default RegistrationForm;
