import { Form, Input, Button } from "antd";
import { UserOutlined, LockOutlined } from "@ant-design/icons";

import "./style.css";
import { appClient } from "../../../api";
import { useNavigate } from "react-router-dom";

function LoginForm() {
  const navigate = useNavigate();
  const onFinish = (values) => {
    appClient.post("/login").then((response) => {
      navigate("/");
    });
  };

  return (
    <Form name="normal_login" className="login-form" onFinish={onFinish}>
      <Form.Item
        name="username"
        rules={[
          {
            required: true,
            message: "Username을 입력해주세요",
          },
        ]}
      >
        <Input
          prefix={<UserOutlined className="site-form-item-icon" />}
          placeholder="Username"
        />
      </Form.Item>
      <Form.Item
        name="password"
        rules={[
          {
            required: true,
            message: "비밀번호를 입력해주세요",
          },
        ]}
      >
        <Input
          prefix={<LockOutlined className="site-form-item-icon" />}
          type="password"
          placeholder="Password"
        />
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit" className="login-form-button">
          Log in
        </Button>
        아직 주차어때의 회원이 아닌가요? <a href="">회원가입</a>
      </Form.Item>
    </Form>
  );
}

export default LoginForm;
