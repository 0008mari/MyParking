import React from "react";
import { useQuery } from "@tanstack/react-query";
import { useNavigate } from "react-router-dom";
import { Button } from "antd";

import { RowFlexWrapper } from "./style";
import { appClient } from "../../../api";

function AverageReview({ parkingId }) {
  const navigate = useNavigate();
  const { data, isLoading } = useQuery(["parkingData", parkingId], async () => {
    const { data } = await appClient.get(`/parkings/${parkingId}`);
    return data;
  });

  if (isLoading) return <div>로딩중</div>;

  // empty state
  if (!data) return <div>비었음</div>;

  return (
    <RowFlexWrapper>
      <div>
        <h2>{data.data.name}</h2>
        <div>{`별점 ${data.data.reviewStarAvg.toFixed(2)}`}</div>
        <div>{data.data.address}</div>
      </div>
      <div>
        <Button type="primary" size="large" onClick={() => navigate("new")}>
          리뷰 남기기
        </Button>
      </div>
    </RowFlexWrapper>
  );
}

export default AverageReview;
