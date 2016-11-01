using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace DistributionProductModule.Model.机构
{
    /// <summary>
    /// 理财师与客户关系
    /// </summary>
    public sealed class FCRelationshipData
    {
        #region 属性
        private int c_ID;
        /// <summary>
        /// 流水号
        /// </summary>
        public int ID {
            get { return c_ID; }
            set { c_ID = value; }
        }

        private string c_CustomerNo;
        /// <summary>
        /// 客户编号
        /// </summary>
        public string CustomerNo {
            get { return c_CustomerNo; }
            set { c_CustomerNo = value; }
        }

        private string c_FinancialCode;
        /// <summary>
        /// 理财师编号
        /// </summary>
        public string FinancialCode {
            get { return c_FinancialCode; }
            set { c_FinancialCode = value; }
        }

        private string c_CreateUser;
        /// <summary>
        /// 创建人
        /// </summary>
        public string CreateUser {
            get { return c_CreateUser; }
            set { c_CreateUser = value; }
        }

        private DateTime c_CreateTime;
        /// <summary>
        /// 创建时间
        /// </summary>
        public DateTime CreateTime {
            get { return c_CreateTime; }
            set { c_CreateTime = value; }
        }

        private string c_FinancialName;
        ///<summary>
        ///理财师姓名
        ///</summary>
        public string FinancialName {
            get { return c_FinancialName; }
            set { c_FinancialName = value; }
        }

        private string c_CustomerName;
        ///<summary>
        ///客户姓名
        ///</summary>
        public string CustomerName {
            get { return c_CustomerName; }
            set { c_CustomerName = value; }
        }

        private string c_CustomerPhone;
        ///<summary>
        ///客户手机
        ///</summary>
        public string CustomerPhone {
            get { return c_CustomerPhone; }
            set { c_CustomerPhone = value; }
        }

        private string c_CustomerIDCard;
        ///<summary>
        ///客户身份证号
        ///</summary>
        public string CustomerIDCard {
            get { return c_CustomerPhone; }
            set { c_CustomerPhone = value; }
        }
        #endregion
    }
}
