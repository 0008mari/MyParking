import { useState } from "react";
import { Button } from "antd";
import { useMutation } from "@tanstack/react-query";

import ReviewList from "./List";

import { PageWrapper } from "./styled";
import { useNavigate, useParams } from "react-router-dom";
import { appClient } from "../../api";

function ReviewNewPage() {
  const [selectedReview, setSelectedReview] = useState({});
  const { parkingId } = useParams();
  const navigate = useNavigate();
  const userId = localStorage.getItem("userId");

  const { mutate } = useMutation(
    () => {
      appClient.post("/reviews/new", {
        userId: userId,
        code: parkingId,
        space: selectedReview[0],
        level: selectedReview[1],
        costefficient: selectedReview[2],
        staff: selectedReview[3],
        revisit: selectedReview[4],
        score: selectedReview[5],
      });
    },
    {
      onSuccess: () => {
        navigate(`/review/${parkingId}`);
      },
    }
  );

  const handleButtonClick = (e, index) => {
    setSelectedReview((prev) => ({ ...prev, [index]: e.target.value }));
  };

  const handleRateClick = (value) => {
    setSelectedReview((prev) => ({ ...prev, 5: value }));
  };

  const handleSubmitReview = (e) => {
    e.preventDefault();
    if (
      !selectedReview[0] ||
      !selectedReview[1] ||
      !selectedReview[2] ||
      !selectedReview[3] ||
      !selectedReview[4] ||
      !selectedReview[5]
    ) {
      alert("모든 항목을 선택해주세요");
      return;
    }

    mutate();
  };

  return (
    <PageWrapper>
      <h1>리뷰쓰기</h1>
      <ReviewList onClick={handleButtonClick} onChange={handleRateClick} />
      <Button size="large" onClick={handleSubmitReview}>
        리뷰 남기기
      </Button>
    </PageWrapper>
  );
}

export default ReviewNewPage;
