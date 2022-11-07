import styled from "styled-components";

const RowFlexWrapper = styled.div`
  display: flex;
  gap: 4px;
`;

const RowFlexWrapperSpace = styled.div`
  display: flex;
  justify-content: space-between;

  align-items: center;
  margin: 20px 0;
`;

const ColumnFlexWrapper = styled.div`
  display: flex;
  flex-direction: column;

  width: 100%;
`;

const ReviewItem = styled.div`
  border: 1px solid black;
  border-radius: 4px;
  padding: 4px;
`;

export { RowFlexWrapper, RowFlexWrapperSpace, ColumnFlexWrapper, ReviewItem };
