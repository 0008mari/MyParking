import React, { useState } from "react";
import { Drawer, Button, Space } from "antd";
import { MenuOutlined } from "@ant-design/icons";
import { Link } from "react-router-dom";

function InfoDrawer() {
  const [visible, setVisible] = useState(false);

  const showDrawer = () => {
    setVisible(true);
  };

  const onClose = () => {
    setVisible(false);
  };

  return (
    <>
      <Space>
        <Button type="text" onClick={showDrawer}>
          <MenuOutlined />
        </Button>
      </Space>
      <Drawer
        title="주차어때"
        placement="left"
        width={"300px"}
        onClose={onClose}
        visible={visible}
      >
        <Link to={"/mypage"}>마이페이지</Link>
      </Drawer>
    </>
  );
}

export default InfoDrawer;
